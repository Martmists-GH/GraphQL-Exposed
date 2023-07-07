package com.example.db.models

import com.example.db.tables.CharacterEnemyTable
import com.example.db.tables.CharacterFriendTable
import com.example.db.tables.CharacterTable
import com.example.db.tables.FilmCharacterTable
import com.martmists.graphql.annotations.GraphQLModel
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

@GraphQLModel
class Character(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Character>(CharacterTable)

    var name by CharacterTable.name
    var species by CharacterTable.species
    var friends by Character.via(CharacterFriendTable.character, CharacterFriendTable.friend)
    var enemies by Character.via(CharacterEnemyTable.character, CharacterEnemyTable.enemy)
    var actors by Person via FilmCharacterTable
    var films by Film via FilmCharacterTable
}