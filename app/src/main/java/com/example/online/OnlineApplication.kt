package com.example.online

import android.app.Application
import androidx.multidex.MultiDexApplication
import androidx.room.Room
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.online.data.database.AppDatabase
import com.example.online.data.database.dao.CourseDao
import com.example.online.data.database.dao.LessonDao
import com.example.online.data.repository.CourseRepository
import com.example.online.data.repository.DatabaseCourseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class OnlineApplication : MultiDexApplication(), Configuration.Provider {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "online_database"
        ).build()
    }

    val courseDao: CourseDao by lazy {
        database.courseDao()
    }

    val lessonDao: LessonDao by lazy {
        database.lessonDao()
    }

    val courseRepository by lazy {
        DatabaseCourseRepository(courseDao, lessonDao)
    }

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch(Dispatchers.IO) {
            initializeData()
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()

    private suspend fun initializeData() {
        // Здесь будет инициализация начальных данных
    }
} 