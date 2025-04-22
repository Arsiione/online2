package com.example.online.data.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val duration: String,
    val level: String,
    val rating: Float = 0f,
    val price: Double = 0.0,
    val lessonsCount: Int = 0,
    val category: String,
    val tags: List<String> = emptyList(),
    val requirements: List<String> = emptyList(),
    val whatYouWillLearn: List<String> = emptyList(),
    val isEnrolled: Boolean = false,
    val progress: Int = 0,
    val isFavorite: Boolean = false
)

data class Lesson(
    val id: Int,
    val courseId: Int,
    val title: String,
    val description: String,
    val duration: String,
    val order: Int,
    val videoUrl: String = "",
    val content: String = "",
    val isCompleted: Boolean = false,
    val lastPosition: Long = 0,
    val attachments: List<String> = emptyList(),
    val hasQuiz: Boolean = false
) 