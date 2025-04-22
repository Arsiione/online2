package com.example.online.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.navigation
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.online.data.AppState
import com.example.online.ui.screens.*
import com.example.online.viewmodel.MainViewModel
import com.example.online.data.model.Course

@Composable
fun AppNavigation(
    navController: NavHostController,
    appState: MutableState<AppState>,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                appState = appState,
                viewModel = viewModel,
                onCourseClick = { courseId: Int ->
                    navController.navigate(Screen.CourseDetails.createRoute(courseId))
                }
            )
        }
        
        composable(route = Screen.Courses.route) {
            CoursesScreen(
                onCourseClick = { course: Course ->
                    navController.navigate(Screen.CourseDetails.createRoute(course.id))
                }
            )
        }
        
        composable(
            route = "${Screen.CourseDetails.route}/{courseId}",
            arguments = listOf(
                navArgument("courseId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getInt("courseId") ?: return@composable
            val course = appState.value.courses.find { it.id == courseId } ?: return@composable
            val lessons = viewModel.getLessonsForCourse(courseId)
            
            CourseDetailsScreen(
                course = course,
                lessons = lessons,
                onLessonClick = { lessonId ->
                    navController.navigate(Screen.LessonDetails.createRoute(lessonId))
                },
                onEnrollClick = {
                    viewModel.enrollInCourse(courseId)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(
            route = "${Screen.LessonDetails.route}/{lessonId}",
            arguments = listOf(
                navArgument("lessonId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getInt("lessonId") ?: return@composable
            LessonDetailsScreen(lessonId = lessonId)
        }
        
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                user = appState.value.currentUser,
                enrolledCourses = appState.value.courses.filter { it.isEnrolled },
                onEditProfile = {
                    // TODO: Implement profile editing
                }
            )
        }
        
        composable(route = Screen.Search.route) {
            SearchScreen(
                courses = appState.value.courses,
                onCourseClick = { course ->
                    navController.navigate(Screen.CourseDetails.createRoute(course.id))
                }
            )
        }
        
        composable(route = Screen.Settings.route) {
            SettingsScreen(
                isDarkTheme = appState.value.isDarkTheme,
                onThemeChange = { isDark: Boolean ->
                    viewModel.setDarkTheme(isDark)
                }
            )
        }
    }
} 