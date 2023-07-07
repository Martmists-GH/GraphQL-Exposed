package com.example.db.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CharacterFriendTable : IntIdTable() {
    val character = reference("character", CharacterTable)
    val friend = reference("friend", CharacterTable)
}