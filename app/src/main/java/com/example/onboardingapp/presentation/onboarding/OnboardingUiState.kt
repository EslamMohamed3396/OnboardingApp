package com.example.onboardingapp.presentation.onboarding


data class OnboardingUiState(
    val currentPage: Int = 0,
    val currentStep: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)


data class OnboardingPageData(
    val title: String,
    val description: String,
    val icon: String
)
