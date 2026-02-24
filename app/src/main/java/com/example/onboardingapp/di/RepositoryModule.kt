package com.example.onboardingapp.di

import com.example.onboardingapp.data.local.UserDataStore
import com.example.onboardingapp.data.repository.UserRepositoryImpl
import com.example.onboardingapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userDataStore: UserDataStore
    ): UserRepository {
        return UserRepositoryImpl(userDataStore)
    }
}
