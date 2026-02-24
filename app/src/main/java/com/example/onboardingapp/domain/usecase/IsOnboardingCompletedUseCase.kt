package com.example.onboardingapp.domain.usecase

import com.example.onboardingapp.domain.repository.UserRepository
import javax.inject.Inject

class IsOnboardingCompletedUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Boolean {
        return userRepository.isOnboardingCompleted().getOrDefault(false)
    }
}
