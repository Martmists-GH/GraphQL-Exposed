package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CharacterEnemyTable : IntIdTable() {
    val character = reference("character", CharacterTable)
    val enemy = reference("enemy", CharacterTable)
}