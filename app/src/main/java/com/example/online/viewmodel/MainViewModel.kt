package com.example.online.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online.data.AppState
import com.example.online.data.model.Course
import com.example.online.data.model.Lesson
import com.example.online.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {
    private val _appState = MutableStateFlow(AppState())
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    private val testLessons = listOf(
        Lesson(
            id = 1,
            courseId = 1,
            title = "Введение в Python",
            description = "Основы языка Python и его синтаксис",
            duration = "45 минут",
            order = 1,
            videoUrl = "https://example.com/video1",
            content = "Python - это высокоуровневый язык программирования...",
            isCompleted = false,
            lastPosition = 0,
            attachments = listOf("https://example.com/slides1.pdf"),
            hasQuiz = true
        ),
        Lesson(
            id = 2,
            courseId = 1,
            title = "Переменные и типы данных",
            description = "Работа с переменными и основными типами данных",
            duration = "60 минут",
            order = 2,
            videoUrl = "https://example.com/video2",
            content = "В Python есть несколько встроенных типов данных...",
            isCompleted = false,
            lastPosition = 0,
            attachments = listOf("https://example.com/slides2.pdf"),
            hasQuiz = true
        ),
        Lesson(
            id = 3,
            courseId = 2,
            title = "Введение в нейронные сети",
            description = "Основные концепции нейронных сетей",
            duration = "90 минут",
            order = 1,
            videoUrl = "https://example.com/video3",
            content = "Нейронные сети - это вычислительные системы...",
            isCompleted = false,
            lastPosition = 0,
            attachments = listOf("https://example.com/slides3.pdf"),
            hasQuiz = true
        )
    )

    init {
        // Add test courses
        val testCourses = listOf(
            Course(
                id = 1,
                title = "Поколение Python: курс для начинающих",
                description = "Полный курс по Python для начинающих программистов",
                imageUrl = "https://c4.wallpaperflare.com/wallpaper/873/975/781/python-programming-minimalism-grey-technology-hd-wallpaper-preview.jpg",
                author = "John Doe",
                duration = "20 часов",
                level = "Начинающий",
                rating = 4.8f,
                lessonsCount = 25,
                category = "Programming",
                isEnrolled = true,
                progress = 73,
                isFavorite = false
            ),
            Course(
                id = 2,
                title = "Нейронные сети и обработка текста",
                description = "Изучите основы нейронных сетей и их применение в обработке текста",
                imageUrl = "https://example.com/neural.jpg",
                author = "Jane Smith",
                duration = "15 часов",
                level = "Продвинутый",
                rating = 4.9f,
                lessonsCount = 18,
                category = "AI",
                isEnrolled = true,
                progress = 0,
                isFavorite = false
            ),
            Course(
                id = 3,
                title = "Android разработка с Kotlin",
                description = "Создавайте современные Android приложения с Kotlin",
                imageUrl = "https://example.com/android.jpg",
                author = "Alex Brown",
                duration = "25 часов",
                level = "Средний",
                rating = 4.7f,
                lessonsCount = 30,
                category = "Mobile",
                isEnrolled = true,
                progress = 45,
                isFavorite = false
            )
        )
        
        _appState.value = _appState.value.copy(
            courses = testCourses,
            lessons = testLessons
        )
    }

    fun getLessonsForCourse(courseId: Int): List<Lesson> {
        return _appState.value.lessons.filter { it.courseId == courseId }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                // TODO: Implement actual login logic
                val user = User(
                    id = 1,
                    username = "testuser",
                    email = email,
                    name = "Test User",
                    avatarUrl = "",
                    bio = "",
                    points = 0,
                    level = 1,
                    rating = 0f,
                    enrolledCourses = emptyList(),
                    completedCourses = emptyList(),
                    achievements = emptyList()
                )
                _appState.value = _appState.value.copy(
                    isLoggedIn = true,
                    currentUser = user,
                    error = null
                )
            } catch (e: Exception) {
                _appState.value = _appState.value.copy(
                    error = e.message ?: "Login failed"
                )
            }
        }
    }

    fun logout() {
        _appState.value = AppState()
    }

    fun toggleFavorite(courseId: Int) {
        val updatedCourses = _appState.value.courses.map { course ->
            if (course.id == courseId) {
                course.copy(isFavorite = !course.isFavorite)
            } else {
                course
            }
        }
        _appState.value = _appState.value.copy(courses = updatedCourses)
    }

    fun enrollInCourse(courseId: Int) {
        viewModelScope.launch {
            val currentCourses = _appState.value.courses
            val updatedCourses = currentCourses.map { course ->
                if (course.id == courseId) {
                    course.copy(isEnrolled = true)
                } else {
                    course
                }
            }
            _appState.value = _appState.value.copy(courses = updatedCourses)
        }
    }

    fun setDarkTheme(isDark: Boolean) {
        _appState.value = _appState.value.copy(isDarkTheme = isDark)
    }
} 