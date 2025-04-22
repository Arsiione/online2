package com.example.online.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "user_course_progress",
    primaryKeys = ["userId", "courseId"],
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
        )
    ]
)
data class UserCourseProgressEntity(
    val userId: Int,
    val courseId: Int,
    val progress: Int = 0, // Общий прогресс в процентах
    val completedLessons: List<Int> = emptyList(), // Список ID завершенных уроков
    val lastViewedLessonId: Int? = null,
    val lastAccessTime: Long = System.currentTimeMillis(),
    val timeSpent: Long = 0, // Время, проведенное в курсе (в минутах)
    val isCompleted: Boolean = false,
    val certificateUrl: String = "", // URL сертификата после завершения
    val rating: Float? = null // Оценка курса пользователем
) 