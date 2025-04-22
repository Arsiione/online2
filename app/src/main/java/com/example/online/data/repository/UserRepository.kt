package com.example.online.data.repository

import com.example.online.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: Int): Flow<User>
    suspend fun updateUser(user: User)
    suspend fun createUser(user: User)
    suspend fun signOut()
    suspend fun getUserByEmail(email: String): User?
} 