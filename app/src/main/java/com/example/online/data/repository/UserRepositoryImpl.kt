package com.example.online.data.repository

import com.example.online.data.database.Users
import com.example.online.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Date

class UserRepositoryImpl : UserRepository {
    override fun getUser(userId: Int): Flow<User> = flow {
        val user = transaction {
            Users.select { Users.id eq userId }
                .map { row ->
                    User(
                        id = row[Users.id],
                        username = row[Users.username],
                        email = row[Users.email],
                        passwordHash = row[Users.passwordHash],
                        createdAt = Date(row[Users.createdAt]),
                        updatedAt = Date(row[Users.updatedAt]),
                        isActive = row[Users.isActive]
                    )
                }
                .firstOrNull()
        }
        emit(user ?: throw Exception("User not found"))
    }

    override suspend fun getUserByEmail(email: String): User? = transaction {
        Users.select { Users.email eq email }
            .map { row ->
                User(
                    id = row[Users.id],
                    username = row[Users.username],
                    email = row[Users.email],
                    passwordHash = row[Users.passwordHash],
                    createdAt = Date(row[Users.createdAt]),
                    updatedAt = Date(row[Users.updatedAt]),
                    isActive = row[Users.isActive]
                )
            }
            .firstOrNull()
    }

    override suspend fun updateUser(user: User) {
        transaction {
            Users.update({ Users.id eq user.id }) {
                it[username] = user.username
                it[email] = user.email
                it[passwordHash] = user.passwordHash
                it[updatedAt] = user.updatedAt.time
                it[isActive] = user.isActive
            }
        }
    }

    override suspend fun signOut() {
        // Реализация выхода из системы
    }

    override suspend fun createUser(user: User) {
        transaction {
            Users.insert {
                it[username] = user.username
                it[email] = user.email
                it[passwordHash] = user.passwordHash
                it[createdAt] = user.createdAt.time
                it[updatedAt] = user.updatedAt.time
                it[isActive] = user.isActive
            }
        }
    }
} 