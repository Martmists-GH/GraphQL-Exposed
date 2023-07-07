package com.martmists.graphql

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getDeclaredProperties
import com.google.devtools.ksp.isAnnotationPresent
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSType
import com.martmists.graphql.annotations.GraphQLModel
import com.martmists.graphql.annotations.HideGraphQLField
import java.io.OutputStreamWriter

class GraphQLProcessor(private val gen: CodeGenerator) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        for (sym in resolver.getSymbolsWithAnnotation("com.martmists.graphql.annotations.GraphQLModel")) {
            processModel(sym as KSClassDeclaration)
        }

        return emptyList()
    }

    // Needs separate handling for:
    // - Base types (Int, String, Enum)
    // - Models (Person, Film)
    // - Iterable (One-to-many, Many-to-many)
    interface Handler {
        val typeName: String
        val get: String
        val set: String
    }

    class DefaultHandler(private val type: KSClassDeclaration, private val fieldName: String) : Handler {
        override val typeName: String
            get() = type.qualifiedName!!.asString()

        override val get: String
            get() = "instance.$fieldName"

        override val set: String
            get() = "instance.$fieldName = value"
    }

    class ModelHandler(private val type: KSClassDeclaration, private val fieldName: String) : Handler {
        override val typeName: String
            get() = "${type.qualifiedName!!.asString()}GraphQL"

        override val get: String
            get() = "instance.$fieldName.graphql"

        override val set: String
            get() = "instance.$fieldName = value.instance"
    }

    class IterableHandler(private val original: Handler, private val fieldName: String) : Handler {
        override val typeName: String
            get() = "Iterable<${original.typeName}>"

        override val get: String
            get() = "instance.$fieldName.map { ${original.typeName}(it) }"

        override val set: String
            get() = "instance.$fieldName = SizedCollection(value.map { it.instance })"
    }

    // Recursive function to create the correct handler
    @OptIn(KspExperimental::class)
    private fun handlerFor(prop: KSType, name: String) : Handler {
        val decl = prop.declaration as KSClassDeclaration

        return if (decl.packageName.asString() == "org.jetbrains.exposed.sql" && decl.simpleName.asString() == "SizedIterable") {
            val origType = prop.arguments[0].type!!.resolve()
            return IterableHandler(handlerFor(origType, name), name)
        } else {
            if (decl.isAnnotationPresent(GraphQLModel::class)) {
                ModelHandler(decl, name)
            } else {
                DefaultHandler(decl, name)
            }
        }
    }

    @OptIn(KspExperimental::class)
    private fun processModel(model: KSClassDeclaration) {
        val ostream = gen.createNewFile(
            Dependencies(true, model.containingFile!!),
            model.packageName.asString(),
            model.simpleName.asString() + "GraphQL",
            "kt",
        )

        OutputStreamWriter(ostream).use {
            with(it) {
                write("package ${model.packageName.asString()}\n\n")

                write("import org.jetbrains.exposed.sql.SizedCollection\n")
                write("import org.jetbrains.exposed.sql.transactions.transaction\n\n")

                write("class ${model.simpleName.asString()}GraphQL(internal val instance: ${model.simpleName.asString()}) {\n")
                write("    val id = instance.id.value\n")

                for (prop in model.getDeclaredProperties()) {
                    if (prop.isAnnotationPresent(HideGraphQLField::class)) {
                        continue
                    }

                    val propName = prop.simpleName.asString()
                    val handler = handlerFor(prop.type.resolve(), propName)

                    if (prop.isMutable) {
                        write("    var $propName: ${handler.typeName}\n")
                    } else {
                        write("    val $propName: ${handler.typeName}\n")
                    }

                    write("        get() = ${handler.get}\n")

                    if (prop.isMutable) {
                        write("        set(value) { ${handler.set} }\n")
                    }
                }

                write("}\n\n")

                write("val ${model.simpleName.asString()}.graphql: ${model.simpleName.asString()}GraphQL\n")
                write("    get() = ${model.simpleName.asString()}GraphQL(this)\n")
            }
        }
    }
}