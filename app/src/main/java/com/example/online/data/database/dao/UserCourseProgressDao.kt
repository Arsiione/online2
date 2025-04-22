package com.example.online.data.database.dao

import androidx.room.*
import com.example.online.data.database.entity.UserCourseProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCourseProgressDao {
    @Query("SELECT * FROM user_course_progress WHERE userId = :userId")
    fun getUserProgress(userId: Int): Flow<List<UserCourseProgressEntity>>

    @Query("SELECT * FROM user_course_progress WHERE userId = :userId AND courseId = :courseId")
    fun getUserCourseProgress(userId: Int, courseId: Int): Flow<UserCourseProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: UserCourseProgressEntity)

    @Update
    suspend fun updateProgress(progress: UserCourseProgressEntity)

    @Delete
    suspend fun deleteProgress(progress: UserCourseProgressEntity)

    @Query("UPDATE user_course_progress SET progress = :progress WHERE userId = :userId AND courseId = :courseId")
    suspend fun updateProgressValue(userId: Int, courseId: Int, progress: Int)

    @Query("UPDATE user_course_progress SET completedLessons = :lessonIds WHERE userId = :userId AND courseId = :courseId")
    suspend fun updateCompletedLessons(userId: Int, courseId: Int, lessonIds: List<Int>)

    @Query("UPDATE user_course_progress SET lastViewedLessonId = :lessonId WHERE userId = :userId AND courseId = :courseId")
    suspend fun updateLastViewedLesson(userId: Int, courseId: Int, lessonId: Int)

    @Query("UPDATE user_course_progress SET timeSpent = timeSpent + :minutes WHERE userId = :userId AND courseId = :courseId")
    suspend fun incrementTimeSpent(userId: Int, courseId: Int, minutes: Long)

    @Query("UPDATE user_course_progress SET isCompleted = :isCompleted WHERE userId = :userId AND courseId = :courseId")
    suspend fun updateCompletionStatus(userId: Int, courseId: Int, isCompleted: Boolean)

    @Query("UPDATE user_course_progress SET rating = :rating WHERE userId = :userId AND courseId = :courseId")
    suspend fun updateRating(userId: Int, courseId: Int, rating: Float)

    @Query("SELECT AVG(rating) FROM user_course_progress WHERE courseId = :courseId AND rating IS NOT NULL")
    fun getCourseAverageRating(courseId: Int): Flow<Float?>
} 