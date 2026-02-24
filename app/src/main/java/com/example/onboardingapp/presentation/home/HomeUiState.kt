package com.example.onboardingapp.presentation.home


data class HomeUiState(
    val currentPage: Int = 0,
    val currentStep: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)
