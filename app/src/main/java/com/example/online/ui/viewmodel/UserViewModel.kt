package com.example.online.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.online.data.model.User
import com.example.online.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun getUser(userId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getUser(userId).collect { user ->
                    _currentUser.value = user
                }
            } catch (e: Exception) {
                _error.emit("Failed to load user: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.updateUser(user)
                _currentUser.value = user
            } catch (e: Exception) {
                _error.emit("Failed to update user: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _currentUser.value = null
            // Additional sign out logic if needed
        }
    }
} 