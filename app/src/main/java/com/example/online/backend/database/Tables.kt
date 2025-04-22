package com.example.online.backend.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object Users : Table() {
    val id = integer("id").autoIncrement()
    val username = varchar("username", 50).uniqueIndex()
    val email = varchar("email", 100).uniqueIndex()
    val passwordHash = varchar("password_hash", 64)
    val name = varchar("name", 100)
    val avatarUrl = varchar("avatar_url", 255).nullable()
    val bio = text("bio").nullable()
    val points = integer("points").default(0)
    val level = integer("level").default(1)
    val createdAt = timestamp("created_at")
    val lastLogin = timestamp("last_login")

    override val primaryKey = PrimaryKey(id)
}

object Courses : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 100)
    val description = text("description")
    val imageUrl = varchar("image_url", 255)
    val author = reference("author_id", Users.id)
    val duration = varchar("duration", 50)
    val level = varchar("level", 20)
    val rating = float("rating").default(0f)
    val price = decimal("price", 10, 2).default(0.toBigDecimal())
    val lessonsCount = integer("lessons_count").default(0)
    val category = varchar("category", 50)
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")

    override val primaryKey = PrimaryKey(id)
}

object Lessons : Table() {
    val id = integer("id").autoIncrement()
    val courseId = reference("course_id", Courses.id)
    val title = varchar("title", 100)
    val description = text("description")
    val content = text("content")
    val duration = varchar("duration", 50)
    val orderInCourse = integer("order_in_course")
    val videoUrl = varchar("video_url", 255).nullable()
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")

    override val primaryKey = PrimaryKey(id)
}

object UserProgress : Table() {
    val id = integer("id").autoIncrement()
    val userId = reference("user_id", Users.id)
    val courseId = reference("course_id", Courses.id)
    val lessonId = reference("lesson_id", Lessons.id).nullable()
    val progress = integer("progress").default(0)
    val isCompleted = bool("is_completed").default(false)
    val lastAccessTime = timestamp("last_access_time")
    val completedAt = timestamp("completed_at").nullable()

    override val primaryKey = PrimaryKey(id)
} 