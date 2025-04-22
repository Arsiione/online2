package com.example.online.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Courses : Screen("courses")
    object Profile : Screen("profile")
    object Search : Screen("search")
    object Settings : Screen("settings")
    
    object CourseDetails : Screen("course_details") {
        fun createRoute(courseId: Int) = "$route/$courseId"
    }
    
    object LessonDetails : Screen("lesson_details") {
        fun createRoute(lessonId: Int) = "$route/$lessonId"
    }
    
    companion object {
        fun fromRoute(route: String): Screen {
            return when (route) {
                Home.route -> Home
                Courses.route -> Courses
                Profile.route -> Profile
                Search.route -> Search
                Settings.route -> Settings
                else -> {
                    if (route.startsWith(CourseDetails.route)) return CourseDetails
                    if (route.startsWith(LessonDetails.route)) return LessonDetails
                    Home
                }
            }
        }
    }
} 