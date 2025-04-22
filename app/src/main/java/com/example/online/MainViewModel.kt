package com.example.online

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online.data.AppState
import com.example.online.data.model.User
import com.example.online.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class MainViewModel : ViewModel() {
    private val _appState = MutableStateFlow(AppState())
    val appState = _appState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _appState.value = _appState.value.copy(isLoading = true)
            try {
                // Имитация запроса к серверу
                val now = Date()
                _appState.value = _appState.value.copy(
                    isLoggedIn = true,
                    currentUser = User(
                        id = 1,
                        username = username,
                        email = "$username@example.com",
                        passwordHash = "", // В реальном приложении здесь должен быть хеш пароля
                        createdAt = now,
                        updatedAt = now,
                        isActive = true
                    ),
                    isLoading = false
                )
            } catch (e: Exception) {
                _appState.value = _appState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun logout() {
        _appState.value = AppState()
    }

    fun updateSearchQuery(query: String) {
        _appState.value = _appState.value.copy(searchQuery = query)
    }

    fun navigateTo(screen: Screen) {
        _appState.value = _appState.value.copy(currentScreen = screen)
    }
} 