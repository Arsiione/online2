package com.example.online.backend.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://localhost:5432/online_courses"
            username = "postgres"
            password = "postgres"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }
        
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
        
        // Создаем таблицы
        transaction {
            SchemaUtils.create(Users, Courses, Lessons, UserProgress)
        }
    }
} 