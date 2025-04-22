package com.example.online.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.online.data.AppState
import com.example.online.navigation.Screen
import com.example.online.ui.components.AppBottomNavigation
import com.example.online.ui.components.NavigationItem
import com.example.online.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appState: MutableState<AppState>,
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                currentRoute = currentRoute,
                onNavigate = { item ->
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItem.Home.route) {
                HomeScreen(appState = appState, viewModel = viewModel) { courseId ->
                    navController.navigate(Screen.CourseDetails.createRoute(courseId))
                }
            }
            composable(NavigationItem.Courses.route) {
                CoursesScreen(
                    onCourseClick = { course ->
                        navController.navigate(Screen.CourseDetails.createRoute(course.id))
                    }
                )
            }
            composable(NavigationItem.AI.route) {
                // TODO: Implement AI Screen
            }
            composable(NavigationItem.Notifications.route) {
                // TODO: Implement Notifications Screen
            }
            composable(NavigationItem.Profile.route) {
                ProfileScreen(
                    user = appState.value.currentUser,
                    enrolledCourses = appState.value.courses.filter { it.isEnrolled },
                    onEditProfile = { /* TODO */ }
                )
            }
            composable(
                route = Screen.CourseDetails.route + "/{courseId}",
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getString("courseId")?.toIntOrNull()
                courseId?.let { id ->
                    val course = appState.value.courses.find { it.id == id }
                    course?.let {
                        // TODO: Implement CourseDetailsScreen
                    }
                }
            }
        }
    }
} 