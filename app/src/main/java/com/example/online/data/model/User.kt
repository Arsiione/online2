package com.example.online.data.model

import java.util.Date
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class User(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val passwordHash: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val bio: String = "",
    val points: Int = 0,
    val level: Int = 1,
    val rating: Float = 0f,
    val enrolledCourses: List<Int> = emptyList(),
    val completedCourses: List<Int> = emptyList(),
    val achievements: List<String> = emptyList(),
    @Contextual val createdAt: Date = Date(),
    @Contextual val updatedAt: Date = Date(),
    val isActive: Boolean = true
) 