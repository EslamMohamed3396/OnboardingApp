package com.example.onboardingapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.domain.usecase.SaveUserNameAndCountryUseCase
import com.example.onboardingapp.domain.usecase.SaveUserNameUseCase
import com.example.onboardingapp.domain.usecase.SaveUserCountryUseCase
import com.example.onboardingapp.domain.usecase.SetOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val setOnboardingCompletedUseCase: SetOnboardingCompletedUseCase,

) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    val onboardingPages = listOf(
        OnboardingPageData(
            title = "onboarding_title_1",
            description = "onboarding_description_1",
            icon = "\uD83D\uDC4B"
        ),
        OnboardingPageData(
            title = "onboarding_title_2",
            description = "onboarding_description_2",
            icon = "\u2728"
        ),
        OnboardingPageData(
            title = "onboarding_title_3",
            description = "onboarding_description_3",
            icon = "\uD83D\uDE80"
        )
    )


    fun completeOnboarding() {
        viewModelScope.launch {
            setOnboardingCompletedUseCase(true)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
        }
    }

    fun skipOnboarding() {
        viewModelScope.launch {
            setOnboardingCompletedUseCase(true)
        }
    }

    fun setCurrentPage(page: Int) {
        _uiState.value = _uiState.value.copy(currentPage = page)
    }
}

