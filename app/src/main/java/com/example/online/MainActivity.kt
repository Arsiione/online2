package com.example.online

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.online.data.AppState
import com.example.online.ui.screens.AuthScreen
import com.example.online.ui.screens.MainScreen
import com.example.online.ui.theme.OnlineLearningAppTheme
import com.example.online.utils.scheduleReminder
import com.example.online.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemInDarkTheme = isSystemInDarkTheme()
            val isDarkTheme = remember { mutableStateOf(systemInDarkTheme) }

            OnlineLearningAppTheme(isDarkTheme = isDarkTheme.value) {
                Surface(
                            modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appState by viewModel.appState.collectAsState()
                    val context = LocalContext.current
                    
                    if (appState.isLoggedIn) {
            LaunchedEffect(Unit) {
                            scheduleReminder(context)
                        }
                        MainScreen(
                            appState = remember { mutableStateOf(appState) },
                            viewModel = viewModel
                        )
                } else {
                        AuthScreen(
                            appState = remember { mutableStateOf(appState) },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}
