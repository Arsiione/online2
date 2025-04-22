package com.example.online.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.online.data.database.entity.LessonEntity
import com.example.online.data.database.entity.CourseEntity
import com.example.online.data.database.entity.UserEntity

@Entity(
    tableName = "user_course_progress",
    primaryKeys = ["userId", "courseId", "lessonId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = LessonEntity::class,
            parentColumns = ["id"],
            childColumns = ["lessonId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserCourseProgress(
    val userId: Int,
    val courseId: Int,
    val lessonId: Int,
    val completed: Boolean,
    val completedAt: Long?
) 