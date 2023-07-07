package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object PersonTable : IntIdTable() {
    val name = varchar("name", 50)
    val yearOfBirth = integer("year_of_birth")
}