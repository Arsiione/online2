package com.example.online.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.online.data.AppState
import com.example.online.data.model.Course
import com.example.online.data.model.User
import com.example.online.ui.components.CurrentCourseCard
import com.example.online.ui.components.EnrolledCoursesSection
import com.example.online.ui.components.PromoBannersSection
import com.example.online.viewmodel.MainViewModel
import com.example.online.ui.components.CategoryChip
import com.example.online.ui.components.CourseCard
import com.example.online.ui.components.EnrolledCourseCard

@Composable
fun HomeScreen(
    appState: MutableState<AppState>,
    viewModel: MainViewModel,
    onCourseClick: (Int) -> Unit
) {
    var showSection by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Основной контент
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
        // Промо баннеры
            item {
        val promoBanners = listOf(
                    "https://png.pngtree.com/background/20230613/original/pngtree-graph-of-stock-prices-on-black-background-picture-image_3425432.jpg" to "Hemme kurslara 50%",
                    "https://lh3.googleusercontent.com/LYUDWiiqyTSiwzbPsJnYhfTzA3kUAoYgRy_1mpKTZOuLtpaMTaNdPKm8Xesm5mxA_zUSIGy6RO4PxhUnIDgTgbmroxgVpudnc0XKWW0cByZXppI2WGo" to "Android täze kurs",
            "https://example.com/promo3.jpg" to "Бесплатная неделя"
        )
        PromoBannersSection(
            banners = promoBanners,
            modifier = Modifier.fillMaxWidth()
        )
            }
        
        // Текущий курс
            item {
        val enrolledCourses = appState.value.courses.filter { it.isEnrolled }
        enrolledCourses.firstOrNull()?.let { currentCourse ->
            Text(
                text = "Продолжить обучение",
                style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
            )
            
            CurrentCourseCard(
                course = currentCourse,
                        onClick = { onCourseClick(currentCourse.id) }
            )
                }
            }

        // Все курсы пользователя
            item {
                val enrolledCourses = appState.value.courses.filter { it.isEnrolled }
        if (enrolledCourses.isNotEmpty()) {
            EnrolledCoursesSection(
                courses = enrolledCourses,
                        onCourseClick = { course: Course -> onCourseClick(course.id) },
                onFavoriteClick = { course: Course ->
                            viewModel.toggleFavorite(course.id)
                        }
                    )
                }
            }
        }

        // Профильная секция поверх основного контента
        AnimatedVisibility(
            visible = showSection,
            enter = fadeIn() + expandIn(expandFrom = Alignment.TopEnd),
            exit = fadeOut() + shrinkOut(shrinkTowards = Alignment.TopEnd),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp)
                .zIndex(1f)
        ) {
            Surface(
                modifier = Modifier
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 4.dp
            ) {
                Column(
        modifier = Modifier
                        .widthIn(min = 200.dp, max = 350.dp)
                        .padding(16.dp)
                ) {
            Text(
                        text = "Привет, ${appState.value.currentUser?.name ?: "Гость"}!",
                style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
            )

                    Spacer(modifier = Modifier.height(8.dp))

            Text(
                        text = "Добро пожаловать обратно",
                style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    appState.value.currentUser?.let {
                        UserStats(user = it)
                    }
                }
            }
        }

        // Иконка пользователя (всегда поверх всего)
        IconButton(
            onClick = { showSection = !showSection },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(24.dp)
                .zIndex(2f)
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = "Профиль пользователя",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun UserStats(user: User) {
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        progress = 1f
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatItem(
            icon = Icons.Rounded.School,
            value = (user.completedCourses.size * progress).toInt().toString(),
            label = "Курсов"
        )

        StatItem(
            icon = Icons.Rounded.Star,
            value = String.format("%.1f", user.rating * progress),
            label = "Рейтинг"
        )

        StatItem(
            icon = Icons.Rounded.EmojiEvents,
            value = (user.points * progress).toInt().toString(),
            label = "Баллов"
        )
    }
}

@Composable
private fun StatItem(
    icon: ImageVector,
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun SearchBar() {
    var searchQuery by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        placeholder = { Text("Kurs gözlemek...") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
            focusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
private fun PromoBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(160.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Ýörite teklip!",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Programmirleme kurslary 50% arzanlaşdy",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }

                FilledTonalButton(
                    onClick = { /* TODO: Navigate to promo */ },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f)
                    )
                ) {
                    Text(
                        "Giňişleýin",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesSection() {
    val categories = listOf(
        "Ählisi",
        "Programmirleme",
        "Dizaýn",
        "Marketing",
        "Diller"
    )
    var selectedCategory by remember { mutableStateOf("Ählisi") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Kategoriýalar",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = categories, key = { it }) { category ->
                CategoryChip(
                    text = category,
                    isSelected = category == selectedCategory,
                    onClick = { selectedCategory = category }
                )
            }
        }
    }
}

@Composable
private fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceVariant
        },
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    actionText: String,
    onActionClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        TextButton(onClick = onActionClick) {
            Text(actionText)
        }
    }
}

@Composable
private fun CourseCard(
    course: Course,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Box {
                AsyncImage(
                    model = course.imageUrl,
                    contentDescription = course.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    contentScale = ContentScale.Crop
                )

                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(bottomEnd = 24.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = course.category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = course.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = course.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = String.format("%.1f", course.rating),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = course.duration,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}