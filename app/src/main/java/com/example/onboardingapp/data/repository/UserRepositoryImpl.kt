package com.example.onboardingapp.data.repository

import com.example.onboardingapp.data.local.UserDataStore
import com.example.onboardingapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val userDataStore: UserDataStore
) : UserRepository {

    override suspend fun isOnboardingCompleted(): Result<Boolean> {
        return try {
            val completed = userDataStore.isOnboardingCompleted()
            Result.success(completed)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun setOnboardingCompleted(completed: Boolean): Result<Unit> {
        return try {
            userDataStore.setOnboardingCompleted(completed)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun saveUserName(name: String): Result<Unit> {
        return try {
            userDataStore.saveUserName(name)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun saveUserCountry(country: String): Result<Unit> {
        return try {
            userDataStore.saveUserCountry(country)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun saveUserNameAndCountry(name: String, country: String): Result<Unit> {
        return try {
            userDataStore.saveUserName(name)
            userDataStore.saveUserCountry(country)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserName(): Result<String> {
        return try {
            val name = userDataStore.userNameFlow.first { it.isNotEmpty() }
            Result.success(name)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserCountry(): Result<String> {
        return try {
            val country = userDataStore.userCountryFlow.first { it.isNotEmpty() }
            Result.success(country)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
