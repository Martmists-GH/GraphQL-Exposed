package com.example

import com.apurebase.kgraphql.GraphQL
import com.apurebase.kgraphql.schema.execution.Executor
import com.example.db.enums.Species
import com.example.db.models.*
import com.example.db.tables.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.tomcat.*
import io.ktor.server.engine.*
import io.ktor.server.request.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val database = Database.connect(
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    transaction(database) {
        SchemaUtils.createMissingTablesAndColumns(
            FilmTable,
            CharacterTable,
            PersonTable,
            CharacterFriendTable,
            CharacterEnemyTable,
            FilmCharacterTable
        )

        // This data is incorrect and I couldn't be bothered to fix it
        // I just asked Copilot to generate a bunch of data lol

        val georgeLucas = Person.new {
            name = "George Lucas"
            yearOfBirth = 1944
        }

        val irvinKershner = Person.new {
            name = "Irvin Kershner"
            yearOfBirth = 1923
        }

        val richardMarquand = Person.new {
            name = "Richard Marquand"
            yearOfBirth = 1937
        }

        val ewanMcGregor = Person.new {
            name = "Ewan McGregor"
            yearOfBirth = 1971
        }

        val liamNeeson = Person.new {
            name = "Liam Neeson"
            yearOfBirth = 1952
        }

        val nataliePortman = Person.new {
            name = "Natalie Portman"
            yearOfBirth = 1981
        }

        val haydenChristensen = Person.new {
            name = "Hayden Christensen"
            yearOfBirth = 1981
        }

        val cristopherLee = Person.new {
            name = "Christopher Lee"
            yearOfBirth = 1922
        }

        val samuelLJackson = Person.new {
            name = "Samuel L. Jackson"
            yearOfBirth = 1948
        }

        val ianMcDiarmid = Person.new {
            name = "Ian McDiarmid"
            yearOfBirth = 1944
        }

        val markHamill = Person.new {
            name = "Mark Hamill"
            yearOfBirth = 1951
        }

        val harrisonFord = Person.new {
            name = "Harrison Ford"
            yearOfBirth = 1942
        }

        val carrieFisher = Person.new {
            name = "Carrie Fisher"
            yearOfBirth = 1956
        }

        val alecGuinness = Person.new {
            name = "Alec Guinness"
            yearOfBirth = 1914
        }

        val davidProwse = Person.new {
            name = "David Prowse"
            yearOfBirth = 1935
        }

        val obiWanKenobi = Character.new {
            name = "Obi-Wan Kenobi"
            species = Species.HUMAN
        }

        val quiGonJinn = Character.new {
            name = "Qui-Gon Jinn"
            species = Species.HUMAN
        }

        val padmeAmidala = Character.new {
            name = "Padmé Amidala"
            species = Species.HUMAN
        }

        val senatorPalpatine = Character.new {
            name = "Senator Palpatine"
            species = Species.HUMAN
        }

        val anakinSkywalker = Character.new {
            name = "Anakin Skywalker"
            species = Species.HUMAN
        }

        val countDooku = Character.new {
            name = "Count Dooku"
            species = Species.HUMAN
        }

        val maceWindu = Character.new {
            name = "Mace Windu"
            species = Species.HUMAN
        }

        val lukeSkywalker = Character.new {
            name = "Luke Skywalker"
            species = Species.HUMAN
        }

        val hanSolo = Character.new {
            name = "Han Solo"
            species = Species.HUMAN
        }

        val princessLeia = Character.new {
            name = "Princess Leia"
            species = Species.HUMAN
        }

        val darthVader = Character.new {
            name = "Darth Vader"
            species = Species.HUMAN
        }

        val ep1 = Film.new {
            name = "Star Wars: Episode I – The Phantom Menace"
            year = 1999
            director = georgeLucas
        }

        val ep2 = Film.new {
            name = "Star Wars: Episode II – Attack of the Clones"
            year = 2002
            director = georgeLucas
        }

        val ep3 = Film.new {
            name = "Star Wars: Episode III – Revenge of the Sith"
            year = 2005
            director = georgeLucas
        }

        val ep4 = Film.new {
            name = "Star Wars: Episode IV – A New Hope"
            year = 1977
            director = georgeLucas
        }

        val ep5 = Film.new {
            name = "Star Wars: Episode V – The Empire Strikes Back"
            year = 1980
            director = irvinKershner
        }

        val ep6 = Film.new {
            name = "Star Wars: Episode VI – Return of the Jedi"
            year = 1983
            director = richardMarquand
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = obiWanKenobi.id
            it[actor] = ewanMcGregor.id
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = quiGonJinn.id
            it[actor] = liamNeeson.id
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = padmeAmidala.id
            it[actor] = nataliePortman.id
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = countDooku.id
            it[actor] = cristopherLee.id
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = maceWindu.id
            it[actor] = samuelLJackson.id
        }

        FilmCharacterTable.insert {
            it[film] = ep1.id
            it[character] = senatorPalpatine.id
            it[actor] = ianMcDiarmid.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = obiWanKenobi.id
            it[actor] = ewanMcGregor.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = anakinSkywalker.id
            it[actor] = haydenChristensen.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = padmeAmidala.id
            it[actor] = nataliePortman.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = countDooku.id
            it[actor] = cristopherLee.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = maceWindu.id
            it[actor] = samuelLJackson.id
        }

        FilmCharacterTable.insert {
            it[film] = ep2.id
            it[character] = senatorPalpatine.id
            it[actor] = ianMcDiarmid.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = obiWanKenobi.id
            it[actor] = ewanMcGregor.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = anakinSkywalker.id
            it[actor] = haydenChristensen.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = padmeAmidala.id
            it[actor] = nataliePortman.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = countDooku.id
            it[actor] = cristopherLee.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = maceWindu.id
            it[actor] = samuelLJackson.id
        }

        FilmCharacterTable.insert {
            it[film] = ep3.id
            it[character] = senatorPalpatine.id
            it[actor] = ianMcDiarmid.id
        }

        FilmCharacterTable.insert {
            it[film] = ep4.id
            it[character] = obiWanKenobi.id
            it[actor] = alecGuinness.id
        }

        FilmCharacterTable.insert {
            it[film] = ep4.id
            it[character] = hanSolo.id
            it[actor] = harrisonFord.id
        }

        FilmCharacterTable.insert {
            it[film] = ep4.id
            it[character] = lukeSkywalker.id
            it[actor] = markHamill.id
        }

        FilmCharacterTable.insert {
            it[film] = ep4.id
            it[character] = princessLeia.id
            it[actor] = carrieFisher.id
        }

        FilmCharacterTable.insert {
            it[film] = ep4.id
            it[character] = darthVader.id
            it[actor] = davidProwse.id
        }

        FilmCharacterTable.insert {
            it[film] = ep5.id
            it[character] = obiWanKenobi.id
            it[actor] = alecGuinness.id
        }

        FilmCharacterTable.insert {
            it[film] = ep5.id
            it[character] = hanSolo.id
            it[actor] = harrisonFord.id
        }

        FilmCharacterTable.insert {
            it[film] = ep5.id
            it[character] = lukeSkywalker.id
            it[actor] = markHamill.id
        }

        FilmCharacterTable.insert {
            it[film] = ep5.id
            it[character] = princessLeia.id
            it[actor] = carrieFisher.id
        }

        FilmCharacterTable.insert {
            it[film] = ep5.id
            it[character] = darthVader.id
            it[actor] = davidProwse.id
        }

        FilmCharacterTable.insert {
            it[film] = ep6.id
            it[character] = obiWanKenobi.id
            it[actor] = alecGuinness.id
        }

        FilmCharacterTable.insert {
            it[film] = ep6.id
            it[character] = hanSolo.id
            it[actor] = harrisonFord.id
        }

        FilmCharacterTable.insert {
            it[film] = ep6.id
            it[character] = lukeSkywalker.id
            it[actor] = markHamill.id
        }

        FilmCharacterTable.insert {
            it[film] = ep6.id
            it[character] = princessLeia.id
            it[actor] = carrieFisher.id
        }

        FilmCharacterTable.insert {
            it[film] = ep6.id
            it[character] = darthVader.id
            it[actor] = davidProwse.id
        }

        obiWanKenobi.friends = SizedCollection(
            quiGonJinn,
            anakinSkywalker,
            padmeAmidala
        )

        obiWanKenobi.enemies = SizedCollection(
            countDooku
        )

        quiGonJinn.friends = SizedCollection(
            obiWanKenobi,
            anakinSkywalker,
            padmeAmidala
        )

        quiGonJinn.enemies = SizedCollection(
            countDooku
        )

        padmeAmidala.friends = SizedCollection(
            obiWanKenobi,
            quiGonJinn,
            anakinSkywalker
        )

        padmeAmidala.enemies = SizedCollection(
            countDooku
        )

        senatorPalpatine.friends = SizedCollection(
            anakinSkywalker
        )

        senatorPalpatine.enemies = SizedCollection(
            maceWindu
        )

        anakinSkywalker.friends = SizedCollection(
            obiWanKenobi,
            quiGonJinn,
            padmeAmidala,
            senatorPalpatine
        )

        anakinSkywalker.enemies = SizedCollection(
            countDooku,
            maceWindu
        )

        countDooku.friends = SizedCollection(
            senatorPalpatine
        )

        countDooku.enemies = SizedCollection(
            obiWanKenobi,
            quiGonJinn,
            padmeAmidala,
            anakinSkywalker,
            maceWindu
        )

        maceWindu.friends = SizedCollection(
            senatorPalpatine
        )

        maceWindu.enemies = SizedCollection(
            anakinSkywalker,
            countDooku
        )

        lukeSkywalker.friends = SizedCollection(
            hanSolo,
            princessLeia
        )

        lukeSkywalker.enemies = SizedCollection(
            darthVader
        )

        hanSolo.friends = SizedCollection(
            lukeSkywalker,
            princessLeia
        )

        hanSolo.enemies = SizedCollection(
            darthVader
        )

        princessLeia.friends = SizedCollection(
            lukeSkywalker,
            hanSolo
        )

        princessLeia.enemies = SizedCollection(
            darthVader
        )

        darthVader.friends = SizedCollection(
            senatorPalpatine,
        )

        darthVader.enemies = SizedCollection(
            lukeSkywalker,
            hanSolo,
            princessLeia
        )
    }

    install(GraphQL) {
        playground = true
        executor = Executor.DataLoaderPrepared
        schema {
            query("films") {
                resolver { ->
                    Film.all().map(Film::graphql)
                }
            }

            query("characters") {
                resolver { ->
                    Character.all().map(Character::graphql)
                }
            }

            query("people") {
                resolver { ->
                    Character.all().map(Character::graphql)
                }
            }

            enum<Species>()
        }
    }

    install(object : Plugin<Application, Unit, Unit> {
        override val key: AttributeKey<Unit> = AttributeKey("GraphQL-Database")

        override fun install(pipeline: Application, configure: Unit.() -> Unit) {
            pipeline.intercept(ApplicationCallPipeline.Plugins) {
                if (call.request.path() == "/graphql" && call.request.httpMethod == HttpMethod.Post) {
                    newSuspendedTransaction {
                        proceed()
                    }
                } else {
                    proceed()
                }
            }
        }
    })
}
