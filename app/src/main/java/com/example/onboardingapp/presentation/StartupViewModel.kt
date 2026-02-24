package com.example.onboardingapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.domain.usecase.IsOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartupViewModel @Inject constructor(
    private val isOnboardingCompletedUseCase: IsOnboardingCompletedUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<StartupUiState>(StartupUiState.Loading)
    val uiState: StateFlow<StartupUiState> = _uiState.asStateFlow()

    init {
        checkOnboardingStatus()
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            delay(300)
            
            try {
                val isCompleted = isOnboardingCompletedUseCase()
                _uiState.value = if (isCompleted) {
                    StartupUiState.OnboardingCompleted
                } else {
                    StartupUiState.OnboardingNotCompleted
                }
            } catch (e: Exception) {
                _uiState.value = StartupUiState.OnboardingNotCompleted
            }
        }
    }
}

sealed class StartupUiState {
    object Loading : StartupUiState()
    object OnboardingCompleted : StartupUiState()
    object OnboardingNotCompleted : StartupUiState()
}
