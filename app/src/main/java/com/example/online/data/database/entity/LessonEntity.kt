package com.example.online.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lessons",
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LessonEntity(
    @PrimaryKey val id: Int,
    val courseId: Int,
    val title: String,
    val description: String,
    val duration: String,
    val order: Int,
    val videoUrl: String = "",
    val content: String = "",
    val isCompleted: Boolean = false,
    val lastPosition: Long = 0, // Позиция видео в миллисекундах
    val attachments: List<String> = emptyList(),
    val hasQuiz: Boolean = false
) 