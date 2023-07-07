package com.example.db.tables

import com.example.db.enums.Species
import org.jetbrains.exposed.dao.id.IntIdTable

object CharacterTable : IntIdTable() {
    val name = varchar("name", 50)
    val species = enumeration<Species>("species")
}