package com.example.online.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.online.data.database.dao.*
import com.example.online.data.database.entity.*

@Database(
    entities = [
        CourseEntity::class,
        LessonEntity::class,
        UserEntity::class,
        UserCourseProgressEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun lessonDao(): LessonDao
    abstract fun userDao(): UserDao
    abstract fun userCourseProgressDao(): UserCourseProgressDao
} 