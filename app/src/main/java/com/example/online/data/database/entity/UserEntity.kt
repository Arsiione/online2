package com.example.online.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val email: String,
    val password: String, // Храним хэш пароля
    val name: String = "",
    val avatarUrl: String = "",
    val bio: String = "",
    val points: Int = 0,
    val level: Int = 1,
    val enrolledCourses: List<Int> = emptyList(), // Список ID курсов
    val completedCourses: List<Int> = emptyList(), // Список ID завершенных курсов
    val achievements: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastLogin: Long = System.currentTimeMillis()
) 