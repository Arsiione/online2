package com.example.online.data.entities

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
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val courseId: Int,
    val title: String,
    val description: String,
    val duration: String,
    val videoUrl: String,
    val orderInCourse: Int
) 