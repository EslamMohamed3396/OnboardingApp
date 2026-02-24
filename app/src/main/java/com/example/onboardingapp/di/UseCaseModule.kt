package com.example.onboardingapp.di

import com.example.onboardingapp.domain.repository.UserRepository
import com.example.onboardingapp.domain.usecase.IsOnboardingCompletedUseCase
import com.example.onboardingapp.domain.usecase.SaveUserNameAndCountryUseCase
import com.example.onboardingapp.domain.usecase.SaveUserNameUseCase
import com.example.onboardingapp.domain.usecase.SaveUserCountryUseCase
import com.example.onboardingapp.domain.usecase.SetOnboardingCompletedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideIsOnboardingCompletedUseCase(
        userRepository: UserRepository
    ): IsOnboardingCompletedUseCase {
        return IsOnboardingCompletedUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserNameUseCase(
        userRepository: UserRepository
    ): SaveUserNameUseCase {
        return SaveUserNameUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserCountryUseCase(
        userRepository: UserRepository
    ): SaveUserCountryUseCase {
        return SaveUserCountryUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserNameAndCountryUseCase(
        userRepository: UserRepository
    ): SaveUserNameAndCountryUseCase {
        return SaveUserNameAndCountryUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSetOnboardingCompletedUseCase(
        userRepository: UserRepository
    ): SetOnboardingCompletedUseCase {
        return SetOnboardingCompletedUseCase(userRepository)
    }
}
