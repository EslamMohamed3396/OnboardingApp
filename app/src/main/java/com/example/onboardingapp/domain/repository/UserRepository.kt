package com.example.onboardingapp.domain.repository


interface UserRepository {

    suspend fun isOnboardingCompleted(): Result<Boolean>

    suspend fun setOnboardingCompleted(completed: Boolean): Result<Unit>
    
    suspend fun saveUserName(name: String): Result<Unit>
    
    suspend fun saveUserCountry(country: String): Result<Unit>
    
    suspend fun saveUserNameAndCountry(name: String, country: String): Result<Unit>
    
    suspend fun getUserName(): Result<String>
    
    suspend fun getUserCountry(): Result<String>
    

}
