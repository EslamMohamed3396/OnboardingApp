package com.example.onboardingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onboardingapp.presentation.StartupUiState
import com.example.onboardingapp.presentation.StartupViewModel
import com.example.onboardingapp.presentation.home.HomeScreen
import com.example.onboardingapp.presentation.navigation.AppNavigation
import com.example.onboardingapp.presentation.navigation.Screen
import com.example.onboardingapp.ui.theme.OnboardingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val viewModel: StartupViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            OnboardingAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

                    when (uiState.value) {
                        is StartupUiState.Loading -> {
                            LoadingScreen()
                        }
                        is StartupUiState.OnboardingCompleted -> {
                            AppNavigation(
                                navController = navController,
                                startDestination = Screen.Home.route
                            )
                        }
                        is StartupUiState.OnboardingNotCompleted -> {
                            AppNavigation(
                                navController = navController,
                                startDestination = Screen.Onboarding.route
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}
