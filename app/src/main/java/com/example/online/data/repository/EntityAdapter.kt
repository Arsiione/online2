package com.example.online.data.repository

import com.example.online.data.database.entity.CourseEntity
import com.example.online.data.database.entity.LessonEntity
import com.example.online.data.database.entity.UserEntity
import com.example.online.data.model.Course
import com.example.online.data.model.Lesson
import com.example.online.data.model.User
import java.util.*

object EntityAdapter {
    fun toCourse(entity: CourseEntity) = Course(
        id = entity.id,
        title = entity.title,
        description = entity.description,
        imageUrl = entity.imageUrl,
        author = entity.author,
        duration = entity.duration,
        level = entity.level,
        rating = entity.rating,
        price = entity.price,
        lessonsCount = entity.lessonsCount,
        category = entity.category,
        tags = entity.tags,
        requirements = entity.requirements,
        whatYouWillLearn = entity.whatYouWillLearn,
        isEnrolled = entity.isEnrolled,
        progress = entity.progress,
        isFavorite = entity.isFavorite
    )

    fun toLesson(entity: LessonEntity) = Lesson(
        id = entity.id,
        courseId = entity.courseId,
        title = entity.title,
        description = entity.description,
        duration = entity.duration,
        order = entity.order,
        videoUrl = entity.videoUrl,
        content = entity.content,
        isCompleted = entity.isCompleted,
        lastPosition = entity.lastPosition,
        attachments = entity.attachments,
        hasQuiz = entity.hasQuiz
    )

    fun toCourseEntity(course: Course) = CourseEntity(
        id = course.id,
        title = course.title,
        description = course.description,
        imageUrl = course.imageUrl,
        author = course.author,
        duration = course.duration,
        level = course.level,
        rating = course.rating,
        price = course.price,
        lessonsCount = course.lessonsCount,
        category = course.category,
        tags = course.tags,
        requirements = course.requirements,
        whatYouWillLearn = course.whatYouWillLearn,
        isEnrolled = course.isEnrolled,
        progress = course.progress,
        isFavorite = course.isFavorite
    )

    fun toLessonEntity(lesson: Lesson) = LessonEntity(
        id = lesson.id,
        courseId = lesson.courseId,
        title = lesson.title,
        description = lesson.description,
        duration = lesson.duration,
        order = lesson.order,
        videoUrl = lesson.videoUrl,
        content = lesson.content,
        isCompleted = lesson.isCompleted,
        lastPosition = lesson.lastPosition,
        attachments = lesson.attachments,
        hasQuiz = lesson.hasQuiz
    )

    fun toUser(entity: UserEntity) = User(
        id = entity.id,
        username = entity.username,
        email = entity.email,
        passwordHash = entity.password,
        name = entity.name,
        avatarUrl = entity.avatarUrl,
        bio = entity.bio,
        points = entity.points,
        level = entity.level,
        rating = 0f,
        enrolledCourses = entity.enrolledCourses,
        completedCourses = entity.completedCourses,
        achievements = entity.achievements,
        createdAt = Date(entity.createdAt),
        updatedAt = Date(entity.lastLogin),
        isActive = true
    )

    fun toUserEntity(user: User) = UserEntity(
        id = user.id,
        username = user.username,
        email = user.email,
        password = user.passwordHash,
        name = user.name,
        avatarUrl = user.avatarUrl,
        bio = user.bio,
        points = user.points,
        level = user.level,
        enrolledCourses = user.enrolledCourses,
        completedCourses = user.completedCourses,
        achievements = user.achievements,
        createdAt = user.createdAt.time,
        lastLogin = user.updatedAt.time
    )
}