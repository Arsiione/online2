package com.example.online.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Float,
    val lessonsCount: Int,
    val category: String
) 