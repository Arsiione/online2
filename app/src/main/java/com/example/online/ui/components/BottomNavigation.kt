package com.example.online.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val label: String
) {
    object Home : NavigationItem(
        "home",
        Icons.Outlined.Public,
        Icons.Rounded.Public,
        "Baş sahypa"
    )
    object Courses : NavigationItem(
        "courses",
        Icons.Outlined.School,
        Icons.Rounded.School,
        "Kurslar"
    )
    object AI : NavigationItem(
        "ai",
        Icons.Outlined.SmartToy,
        Icons.Rounded.SmartToy,
        "AI"
    )
    object Notifications : NavigationItem(
        "notifications",
        Icons.Outlined.Notifications,
        Icons.Rounded.Notifications,
        "Habarlar"
    )
    object Profile : NavigationItem(
        "profile",
        Icons.Outlined.Person,
        Icons.Rounded.Person,
        "Profil"
    )
}

@Composable
fun AppBottomNavigation(
    currentRoute: String,
    onNavigate: (NavigationItem) -> Unit
) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Courses,
        NavigationItem.AI,
        NavigationItem.Notifications,
        NavigationItem.Profile
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .zIndex(10f)
    ) {
        // Основная панель навигации
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .shadow(
                    elevation = 24.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                ),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, item ->
                    if (index == items.size / 2) {
                        // Пустое место для центральной кнопки
                        Spacer(modifier = Modifier.width(56.dp))
                    }
                    
                    if (item != NavigationItem.AI) {
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = { onNavigate(item) },
                            item = item
                        )
                    }
                }
            }
        }

        // Плавающая кнопка AI
        FloatingActionButton(
            onClick = { onNavigate(NavigationItem.AI) },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .size(56.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                    spotColor = Color(0xFF4CAF50).copy(alpha = 0.1f),
                    ambientColor = Color(0xFF4CAF50).copy(alpha = 0.1f)
                ),
            containerColor = Color(0xFF4CAF50),
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 4.dp
            )
        ) {
            val isSelected = currentRoute == NavigationItem.AI.route
            Icon(
                imageVector = if (isSelected) 
                    NavigationItem.AI.selectedIcon 
                else 
                    NavigationItem.AI.icon,
                contentDescription = NavigationItem.AI.label,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
private fun NavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    item: NavigationItem
) {
    val iconSize = if (selected) 24.dp else 20.dp
    
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .clip(CircleShape)
            .background(
                if (selected) 
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else 
                    Color.Transparent
            )
            .padding(6.dp)
    ) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.icon,
            contentDescription = item.label,
            tint = if (selected)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.size(iconSize)
        )
    }
}