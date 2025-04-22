package com.example.online

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AnimatedTreeLogo(
    modifier: Modifier = Modifier,
    size: Dp = 120.dp
) {
    var isVisible by remember { mutableStateOf(false) }
    var treeScale by remember { mutableStateOf(0f) }
    var rootsScale by remember { mutableStateOf(0f) }
    var rotation by remember { mutableStateOf(0f) }
    
    LaunchedEffect(Unit) {
        isVisible = true
        // Анимация появления дерева
        launch {
            animate(0f, 1f, animationSpec = tween(1000)) { value, _ ->
                treeScale = value
            }
        }
        // Анимация появления корней
        launch {
            animate(0f, 1f, animationSpec = tween(1000, delayMillis = 500)) { value, _ ->
                rootsScale = value
            }
        }
        // Бесконечная анимация вращения
        while(true) {
            animate(0f, 360f, animationSpec = tween(3000, easing = LinearEasing)) { value, _ ->
                rotation = value
            }
        }
    }

    Box(
        modifier = modifier
            .size(size)
            .scale(if (isVisible) 1f else 0f)
            .rotate(rotation),
        contentAlignment = Alignment.Center
    ) {
        // Крона дерева (зеленая часть)
        Box(
            modifier = Modifier
                .size(size * 0.8f)
                .scale(treeScale)
                .offset(y = -size * 0.1f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4ADE80), // Яркий зеленый
                            Color(0xFF22C55E)  // Темный зеленый
                        )
                    ),
                    shape = CircleShape
                )
        )
        
        // Ствол и корни (фиолетовая часть)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Ствол
            Box(
                modifier = Modifier
                    .width(size * 0.15f)
                    .height(size * 0.4f)
                    .scale(rootsScale)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF8B5CF6), // Светлый фиолетовый
                                Color(0xFF6D28D9)  // Темный фиолетовый
                            )
                        ),
                        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
            )
            
            // Корни
            Box(
                modifier = Modifier
                    .width(size * 0.6f)
                    .height(size * 0.3f)
                    .scale(rootsScale)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF6D28D9),  // Темный фиолетовый
                                Color(0xFF4C1D95)   // Очень темный фиолетовый
                            )
                        ),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
            )
        }
    }
} 