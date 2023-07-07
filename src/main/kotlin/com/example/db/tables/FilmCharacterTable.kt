package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object FilmCharacterTable : IntIdTable() {
    val film = reference("film", FilmTable)
    val character = reference("character", CharacterTable)
    val actor = reference("actor", PersonTable)
}