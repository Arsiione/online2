package com.example.online.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Основные цвета приложения
private val Green40 = Color(0xFF2E7D32)
private val Green80 = Color(0xFF4CAF50)
private val Green90 = Color(0xFF8BC34A)
private val GreenGrey40 = Color(0xFF455A64)
private val GreenGrey60 = Color(0xFF78909C)
private val GreenGrey90 = Color(0xFFCFD8DC)

@Composable
fun OnlineLearningAppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) {
        darkColorScheme(
            primary = Green80,
            onPrimary = Color.White,
            primaryContainer = Green40,
            onPrimaryContainer = Color.White,
            secondary = GreenGrey60,
            onSecondary = Color.White,
            secondaryContainer = GreenGrey40,
            onSecondaryContainer = Color.White,
            tertiary = Green90,
            onTertiary = Color.Black,
            background = Color(0xFF1A1C19),
            onBackground = Color(0xFFE2E3DE),
            surface = Color(0xFF1A1C19),
            onSurface = Color(0xFFE2E3DE),
            surfaceVariant = GreenGrey40,
            onSurfaceVariant = Color(0xFFC4C8C2),
            outline = GreenGrey60
        )
    } else {
        lightColorScheme(
            primary = Green40,
            onPrimary = Color.White,
            primaryContainer = Green90,
            onPrimaryContainer = Color(0xFF002105),
            secondary = GreenGrey40,
            onSecondary = Color.White,
            secondaryContainer = GreenGrey90,
            onSecondaryContainer = Color(0xFF1A1C19),
            tertiary = Green80,
            onTertiary = Color.White,
            background = Color(0xFFFBFDF7),
            onBackground = Color(0xFF1A1C19),
            surface = Color(0xFFFBFDF7),
            onSurface = Color(0xFF1A1C19),
            surfaceVariant = Color(0xFFDEE5D8),
            onSurfaceVariant = Color(0xFF424940),
            outline = GreenGrey60
        )
    }

    val typography = Typography(
        headlineLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = (-0.2).sp
        ),
        headlineMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = (-0.2).sp
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        labelLarge = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
} 