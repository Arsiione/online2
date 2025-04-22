package com.example.online.backend.service

import com.example.online.data.model.User
import com.example.online.data.repository.UserRepositoryImpl
import com.example.online.backend.utils.PasswordUtils
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*
import kotlinx.coroutines.flow.first

class AuthService(private val userRepository: UserRepositoryImpl) {
    companion object {
        private const val SECRET = "your-secret-key" // В реальном приложении должен храниться в защищенном месте
        private const val ISSUER = "online-courses"
        private const val AUDIENCE = "users"
        private const val VALIDITY_IN_MS = 36_000_00 * 10 // 10 hours
    }

    suspend fun login(email: String, password: String): AuthResponse? {
        val user = userRepository.getUserByEmail(email) ?: return null
        
        if (!PasswordUtils.verifyPassword(password, user.passwordHash)) {
            return null
        }
        
        return AuthResponse(
            token = generateToken(user),
            user = user
        )
    }

    suspend fun register(username: String, email: String, password: String): AuthResponse {
        // Валидация пароля
        if (password.length < 8) {
            throw IllegalArgumentException("Password must be at least 8 characters long")
        }
        
        // Хешируем пароль
        val passwordHash = PasswordUtils.hashPassword(password)
        val now = Date()
        
        // Создаем пользователя
        val user = User(
            username = username,
            email = email,
            passwordHash = passwordHash,
            createdAt = now,
            updatedAt = now,
            isActive = true
        )
        
        userRepository.createUser(user)
        
        return AuthResponse(
            token = generateToken(user),
            user = user
        )
    }

    private fun generateToken(user: User): String = JWT.create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim("userId", user.id)
        .withClaim("email", user.email)
        .withExpiresAt(Date(System.currentTimeMillis() + VALIDITY_IN_MS))
        .sign(Algorithm.HMAC256(SECRET))
}

data class AuthResponse(
    val token: String,
    val user: User
) 