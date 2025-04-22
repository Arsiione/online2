package com.example.online.data.database.dao

import androidx.room.*
import com.example.online.data.database.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE id = :courseId")
    fun getCourseById(courseId: Int): Flow<CourseEntity?>

    @Query("SELECT * FROM courses WHERE category = :category")
    fun getCoursesByCategory(category: String): Flow<List<CourseEntity>>

    @Query("SELECT * FROM courses WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchCourses(query: String): Flow<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Delete
    suspend fun deleteCourse(course: CourseEntity)

    @Query("DELETE FROM courses")
    suspend fun deleteAllCourses()

    @Query("SELECT * FROM courses WHERE isEnrolled = 1")
    fun getEnrolledCourses(): Flow<List<CourseEntity>>

    @Query("UPDATE courses SET isEnrolled = :isEnrolled WHERE id = :courseId")
    suspend fun updateEnrollmentStatus(courseId: Int, isEnrolled: Boolean)

    @Query("UPDATE courses SET progress = :progress WHERE id = :courseId")
    suspend fun updateProgress(courseId: Int, progress: Int)

    @Query("UPDATE courses SET isFavorite = :isFavorite WHERE id = :courseId")
    suspend fun updateFavoriteStatus(courseId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM courses WHERE isFavorite = 1")
    fun getFavoriteCourses(): Flow<List<CourseEntity>>
} 