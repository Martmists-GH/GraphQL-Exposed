package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object FilmTable : IntIdTable() {
    val name = varchar("name", 50)
    val year = integer("year")
    val director = reference("director", PersonTable)
}

