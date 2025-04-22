package com.example.online.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 24.dp,
    val spaceExtraLarge: Dp = 32.dp,
    
    val iconSmall: Dp = 12.dp,
    val iconMedium: Dp = 24.dp,
    val iconLarge: Dp = 32.dp,
    
    val cardElevation: Dp = 2.dp,
    val cardCornerRadius: Dp = 16.dp,
    
    val buttonHeight: Dp = 56.dp,
    val buttonCornerRadius: Dp = 12.dp,
    
    val progressIndicatorHeight: Dp = 4.dp,
    
    val courseCardWidth: Dp = 280.dp,
    val courseCardImageHeight: Dp = 120.dp,
    
    val promoBannerWidth: Dp = 300.dp,
    val promoBannerHeight: Dp = 120.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() } 