package com.example.online.data.database

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50).uniqueIndex()
    val email = varchar("email", 100).uniqueIndex()
    val passwordHash = varchar("password_hash", 100)
    val createdAt = long("created_at")
    val updatedAt = long("updated_at")
    val isActive = bool("is_active").default(true)

    override val primaryKey = PrimaryKey(id)
} 