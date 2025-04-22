package com.example.online.data.database.dao

import androidx.room.*
import com.example.online.data.database.entity.LessonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons WHERE courseId = :courseId ORDER BY `order`")
    fun getLessonsByCourseId(courseId: Int): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    fun getLessonById(lessonId: Int): Flow<LessonEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)

    @Query("DELETE FROM lessons WHERE courseId = :courseId")
    suspend fun deleteLessonsByCourseId(courseId: Int)

    @Query("SELECT * FROM lessons WHERE courseId = :courseId AND isCompleted = 1")
    fun getCompletedLessons(courseId: Int): Flow<List<LessonEntity>>

    @Query("UPDATE lessons SET isCompleted = :isCompleted WHERE id = :lessonId")
    suspend fun updateLessonCompletionStatus(lessonId: Int, isCompleted: Boolean)

    @Query("UPDATE lessons SET lastPosition = :position WHERE id = :lessonId")
    suspend fun updateLessonProgress(lessonId: Int, position: Long)

    @Query("SELECT COUNT(*) FROM lessons WHERE courseId = :courseId AND isCompleted = 1")
    fun getCompletedLessonsCount(courseId: Int): Flow<Int>
} 