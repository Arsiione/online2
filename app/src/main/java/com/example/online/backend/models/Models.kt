package com.example.online.backend.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserDTO(
    val id: Int? = null,
    val username: String,
    val email: String,
    val name: String,
    val avatarUrl: String? = null,
    val bio: String? = null,
    val points: Int = 0,
    val level: Int = 1
)

@Serializable
data class CourseDTO(
    val id: Int? = null,
    val title: String,
    val description: String,
    val imageUrl: String,
    val authorId: Int,
    val duration: String,
    val level: String,
    val rating: Float = 0f,
    val price: Double = 0.0,
    val lessonsCount: Int = 0,
    val category: String
)

@Serializable
data class LessonDTO(
    val id: Int? = null,
    val courseId: Int,
    val title: String,
    val description: String,
    val content: String,
    val duration: String,
    val orderInCourse: Int,
    val videoUrl: String? = null
)

@Serializable
data class UserProgressDTO(
    val id: Int? = null,
    val userId: Int,
    val courseId: Int,
    val lessonId: Int? = null,
    val progress: Int = 0,
    val isCompleted: Boolean = false
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val name: String
)

@Serializable
data class AuthResponse(
    val token: String,
    val user: UserDTO
) 