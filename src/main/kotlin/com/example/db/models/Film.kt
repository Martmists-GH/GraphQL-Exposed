package com.example.db.models

import com.example.db.tables.FilmCharacterTable
import com.example.db.tables.FilmTable
import com.martmists.graphql.annotations.GraphQLModel
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


@GraphQLModel
class Film(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Film>(FilmTable)

    var name by FilmTable.name
    var year by FilmTable.year
    var director by Person referencedOn FilmTable.director
    var characters by Character via FilmCharacterTable
    var actors by Person via FilmCharacterTable
}
