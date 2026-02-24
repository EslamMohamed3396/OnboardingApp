package com.example.onboardingapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onboardingapp.domain.usecase.SaveUserCountryUseCase
import com.example.onboardingapp.domain.usecase.SaveUserNameAndCountryUseCase
import com.example.onboardingapp.domain.usecase.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveUserNameUseCase: SaveUserNameUseCase,
    private val saveUserNameAndCountryUseCase: SaveUserNameAndCountryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    val aiOnboardingSteps = listOf(
        HomeQuestions(
            question = "What is your name?",
            placeholder = "Name",
            prefix = "My name is"
        ),
        HomeQuestions(
            question = "What is your country?",
            placeholder = "Country",
            prefix = "I'm from"
        )
    )

    fun saveName(name: String) {
        viewModelScope.launch {
            saveUserNameUseCase(name)
        }
    }


    fun saveNameAndCountry(name: String, country: String) {
        viewModelScope.launch {
            saveUserNameAndCountryUseCase(name, country)
        }
    }



    fun setCurrentStep(step: Int) {
        _uiState.value = _uiState.value.copy(currentStep = step)
    }
}
data class HomeQuestions(
    val question: String,
    val placeholder: String,
    val prefix: String
)


