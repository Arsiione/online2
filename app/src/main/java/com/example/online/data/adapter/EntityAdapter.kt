package com.example.online.data.adapter

import com.example.online.data.model.Course
import com.example.online.data.model.Lesson
import com.example.online.data.database.entity.CourseEntity
import com.example.online.data.database.entity.LessonEntity

object EntityAdapter {
    fun toCourse(courseEntity: CourseEntity): Course {
        return Course(
            id = courseEntity.id,
            title = courseEntity.title,
            description = courseEntity.description,
            imageUrl = courseEntity.imageUrl,
            author = courseEntity.author,
            duration = courseEntity.duration,
            level = courseEntity.level,
            rating = courseEntity.rating,
            price = courseEntity.price,
            lessonsCount = courseEntity.lessonsCount,
            category = courseEntity.category,
            tags = courseEntity.tags,
            requirements = courseEntity.requirements,
            whatYouWillLearn = courseEntity.whatYouWillLearn,
            isEnrolled = courseEntity.isEnrolled,
            progress = courseEntity.progress
        )
    }

    fun toCourseList(courseEntities: List<CourseEntity>): List<Course> {
        return courseEntities.map { toCourse(it) }
    }

    fun toLesson(lessonEntity: LessonEntity): Lesson {
        return Lesson(
            id = lessonEntity.id,
            courseId = lessonEntity.courseId,
            title = lessonEntity.title,
            description = lessonEntity.description,
            duration = lessonEntity.duration,
            order = lessonEntity.order,
            videoUrl = lessonEntity.videoUrl,
            content = lessonEntity.content,
            isCompleted = lessonEntity.isCompleted,
            lastPosition = lessonEntity.lastPosition,
            attachments = lessonEntity.attachments,
            hasQuiz = lessonEntity.hasQuiz
        )
    }

    fun toLessonList(lessonEntities: List<LessonEntity>): List<Lesson> {
        return lessonEntities.map { toLesson(it) }
    }
} 