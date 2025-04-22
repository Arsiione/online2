package com.example.online.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val email: String,
    val name: String,
    val avatarUrl: String,
    val points: Int
) 