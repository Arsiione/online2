package com.example.online.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online.data.model.Course
import com.example.online.data.repository.CourseRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CourseViewModel(
    private val repository: CourseRepository
) : ViewModel() {
    private val _isOffline = MutableStateFlow(false)
    val isOffline = _isOffline.asStateFlow()

    val courses = repository.getAllCourses()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun getCoursesByCategory(category: String) = repository.getCoursesByCategory(category)
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun getLessonsByCourseId(courseId: Int) = repository.getLessonsByCourseId(courseId)
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun downloadCourseForOffline(courseId: Int) {
        viewModelScope.launch {
            try {
                repository.downloadCourseForOffline(courseId)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun checkOfflineStatus() {
        viewModelScope.launch {
            _isOffline.value = repository.isOfflineMode()
        }
    }
} 