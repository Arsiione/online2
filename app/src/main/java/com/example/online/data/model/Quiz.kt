package com.example.online.data.model

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