package com.example.online.data.repository

import com.example.online.data.database.dao.CourseDao
import com.example.online.data.database.dao.LessonDao
import com.example.online.data.database.entity.CourseEntity
import com.example.online.data.database.entity.LessonEntity
import com.example.online.data.model.Course
import com.example.online.data.model.Lesson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CourseRepository {
    fun getAllCourses(): Flow<List<Course>>
    fun getCoursesByCategory(category: String): Flow<List<Course>>
    fun getLessonsByCourseId(courseId: Int): Flow<List<Lesson>>
    fun getFavoriteCourses(): Flow<List<Course>>
    suspend fun saveCourse(course: Course)
    suspend fun downloadCourseForOffline(courseId: Int)
    suspend fun isOfflineMode(): Boolean
    suspend fun toggleFavorite(courseId: Int, isFavorite: Boolean)
}

class DatabaseCourseRepository(
    private val courseDao: CourseDao,
    private val lessonDao: LessonDao
) : CourseRepository {

    override fun getAllCourses(): Flow<List<Course>> {
        return courseDao.getAllCourses().map { entities ->
            entities.map { entity -> EntityAdapter.toCourse(entity) }
        }
    }

    override fun getCoursesByCategory(category: String): Flow<List<Course>> {
        return courseDao.getCoursesByCategory(category).map { entities ->
            entities.map { entity -> EntityAdapter.toCourse(entity) }
        }
    }

    override fun getLessonsByCourseId(courseId: Int): Flow<List<Lesson>> {
        return lessonDao.getLessonsByCourseId(courseId).map { entities ->
            entities.map { entity -> EntityAdapter.toLesson(entity) }
        }
    }

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return courseDao.getFavoriteCourses().map { entities ->
            entities.map { entity -> EntityAdapter.toCourse(entity) }
        }
    }

    override suspend fun saveCourse(course: Course) {
        val courseEntity = EntityAdapter.toCourseEntity(course)
        courseDao.insertCourse(courseEntity)
    }

    override suspend fun downloadCourseForOffline(courseId: Int) {
        // Implement offline download logic
    }

    override suspend fun isOfflineMode(): Boolean {
        // Implement offline mode check
        return false
    }

    override suspend fun toggleFavorite(courseId: Int, isFavorite: Boolean) {
        courseDao.updateFavoriteStatus(courseId, isFavorite)
    }
} 