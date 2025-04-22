package com.example.online.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
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