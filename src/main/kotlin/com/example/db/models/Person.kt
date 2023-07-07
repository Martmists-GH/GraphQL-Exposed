package com.example.db.models

import com.example.db.tables.CharacterTable
import com.example.db.tables.FilmCharacterTable
import com.example.db.tables.PersonTable
import com.martmists.graphql.annotations.GraphQLModel
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

@GraphQLModel
class Person(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Person>(PersonTable)

    var name by PersonTable.name
    var yearOfBirth by PersonTable.yearOfBirth

    var characters by Character via FilmCharacterTable
    var films by Film via FilmCharacterTable
}
