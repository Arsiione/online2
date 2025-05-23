package com.example.online.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LessonDetailsScreen(
    lessonId: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lesson Details: $lessonId",
            style = MaterialTheme.typography.headlineMedium
        )
        // TODO: Implement lesson details
    }
} 