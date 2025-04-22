package com.example.online.data.database.dao

import androidx.room.*
import com.example.online.data.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE username = :username")
    fun getUserByUsername(username: String): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("UPDATE users SET points = points + :points WHERE id = :userId")
    suspend fun addPoints(userId: Int, points: Int)

    @Query("UPDATE users SET level = :level WHERE id = :userId")
    suspend fun updateLevel(userId: Int, level: Int)

    @Query("UPDATE users SET lastLogin = :timestamp WHERE id = :userId")
    suspend fun updateLastLogin(userId: Int, timestamp: Long)

    @Query("UPDATE users SET enrolledCourses = :courseIds WHERE id = :userId")
    suspend fun updateEnrolledCourses(userId: Int, courseIds: List<Int>)

    @Query("UPDATE users SET completedCourses = :courseIds WHERE id = :userId")
    suspend fun updateCompletedCourses(userId: Int, courseIds: List<Int>)

    @Query("UPDATE users SET achievements = :achievements WHERE id = :userId")
    suspend fun updateAchievements(userId: Int, achievements: List<String>)
} 