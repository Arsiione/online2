package com.example.online.data

import com.example.online.data.model.Quiz
import com.example.online.data.model.Question

data class Lesson(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val videoUrl: String = "",
    val content: String = "",
    val quizzes: List<Quiz> = emptyList(),
    val isCompleted: Boolean = false,
    val order: Int
)

data class Quiz(
    val id: Int,
    val title: String,
    val questions: List<Question>,
    val timeLimit: Int = 0,
    val passingScore: Int = 70,
    val isCompleted: Boolean = false,
    val score: Int = 0
)

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswer: Int,
    val explanation: String = ""
) 