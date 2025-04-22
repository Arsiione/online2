package com.example.online.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Float,
    val lessonsCount: Int,
    val category: String
)

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: Int,
    val courseId: Int,
    val title: String,
    val content: String,
    val duration: String,
    val videoUrl: String,
    val orderInCourse: Int
)

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val username: String,
    val email: String,
    val name: String,
    val avatarUrl: String,
    val enrolledCourses: List<Int>,
    val completedCourses: List<Int>,
    val progress: Map<Int, Set<Int>>,
    val points: Int,
    val level: Int,
    val achievements: List<Int>
) 