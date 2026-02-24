package com.example.onboardingapp.domain.usecase

import com.example.onboardingapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserNameAndCountryUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(name: String, country: String): Result<Unit> {
        return userRepository.saveUserNameAndCountry(name, country)
    }
}
