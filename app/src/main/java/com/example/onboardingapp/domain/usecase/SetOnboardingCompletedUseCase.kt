package com.example.onboardingapp.domain.usecase

import com.example.onboardingapp.domain.repository.UserRepository
import javax.inject.Inject

class SetOnboardingCompletedUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(completed: Boolean): Result<Unit> {
        return userRepository.setOnboardingCompleted(completed)
    }
}
