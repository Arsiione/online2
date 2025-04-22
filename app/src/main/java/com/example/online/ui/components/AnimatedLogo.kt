package com.example.online.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnimatedTreeLogo(
    modifier: Modifier = Modifier,
    treeColor: Color = Color(0xFF4ADE80),
    rootColor: Color = Color(0xFF8B5CF6)
) {
    var isVisible by remember { mutableStateOf(false) }
    
    // Анимации для появления и движения
    val infiniteTransition = rememberInfiniteTransition()
    
    // Анимация пульсации
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    // Анимация вращения гексагонов
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing)
        )
    )
    
    // Анимация появления
    LaunchedEffect(Unit) {
        isVisible = true
    }
    
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Рисуем корни
            drawRoots(rootColor, scale)
            
            // Рисуем крону дерева из гексагонов
            rotate(rotation) {
                drawHexagonalCrown(treeColor, scale)
            }
        }
    }
}

private fun DrawScope.drawHexagonalCrown(color: Color, scale: Float) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val hexRadius = size.width.coerceAtMost(size.height) * 0.1f
    
    // Создаем сетку гексагонов
    val hexagons = mutableListOf<Path>()
    val layers = 3
    
    for (layer in 0..layers) {
        val hexCount = if (layer == 0) 1 else layer * 6
        val radius = layer * hexRadius * 1.5f
        
        for (hex in 0 until hexCount) {
            val angle = (hex.toFloat() / hexCount) * 2 * PI
            val x = centerX + (radius * cos(angle)).toFloat()
            val y = centerY + (radius * sin(angle)).toFloat()
            
            hexagons.add(createHexagonPath(x, y, hexRadius * scale))
        }
    }
    
    // Рисуем все гексагоны
    hexagons.forEach { path ->
        drawPath(
            path = path,
            color = color.copy(alpha = 0.8f)
        )
    }
}

private fun DrawScope.drawRoots(color: Color, scale: Float) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val trunkWidth = size.width * 0.1f
    val rootLength = size.height * 0.3f
    
    // Ствол
    drawRect(
        color = color,
        topLeft = Offset(centerX - trunkWidth / 2, centerY),
        size = androidx.compose.ui.geometry.Size(trunkWidth, rootLength * scale)
    )
    
    // Корни
    val rootPaths = mutableListOf<Path>()
    val rootCount = 5
    
    for (i in 0 until rootCount) {
        val angle = (i.toFloat() / rootCount) * PI - PI / 2
        val path = Path().apply {
            moveTo(centerX, centerY + rootLength * scale)
            quadraticBezierTo(
                centerX + (rootLength * cos(angle)).toFloat() * 0.5f,
                centerY + rootLength * scale + (rootLength * sin(angle)).toFloat() * 0.5f,
                centerX + (rootLength * cos(angle)).toFloat(),
                centerY + rootLength * scale + (rootLength * sin(angle)).toFloat()
            )
        }
        rootPaths.add(path)
    }
    
    // Рисуем корни
    rootPaths.forEach { path ->
        drawPath(
            path = path,
            color = color.copy(alpha = 0.6f)
        )
    }
}

private fun createHexagonPath(centerX: Float, centerY: Float, radius: Float): Path {
    return Path().apply {
        val angles = (0..5).map { it * PI / 3 }
        
        moveTo(
            centerX + (radius * cos(angles[0])).toFloat(),
            centerY + (radius * sin(angles[0])).toFloat()
        )
        
        for (i in 1..5) {
            lineTo(
                centerX + (radius * cos(angles[i])).toFloat(),
                centerY + (radius * sin(angles[i])).toFloat()
            )
        }
        
        close()
    }
} 