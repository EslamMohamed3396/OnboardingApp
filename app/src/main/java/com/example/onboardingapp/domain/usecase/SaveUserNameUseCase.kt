package com.example.onboardingapp.domain.usecase

import com.example.onboardingapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(name: String): Result<Unit> {
        return userRepository.saveUserName(name)
    }
}
