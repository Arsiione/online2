package com.example.online.data

import com.example.online.navigation.Screen
import com.example.online.data.model.User
import com.example.online.data.model.Course
import com.example.online.data.model.Lesson

data class AppState(
    val isLoggedIn: Boolean = false,
    val currentUser: User? = null,
    val currentCourse: Course? = null,
    val currentLesson: Lesson? = null,
    val courses: List<Course> = emptyList(),
    val lessons: List<Lesson> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentScreen: Screen = Screen.Home,
    val isDarkTheme: Boolean = false
) 