package com.example.onboardingapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserDataStore(private val context: Context) {

    private val KEY_ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    private val KEY_USER_NAME = stringPreferencesKey("user_name")
    private val KEY_USER_COUNTRY = stringPreferencesKey("user_country")


    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_NAME] = name
        }
    }

    suspend fun saveUserCountry(country: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USER_COUNTRY] = country
        }
    }

    val userNameFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[KEY_USER_NAME] ?: ""
    }

    val userCountryFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[KEY_USER_COUNTRY] ?: ""
    }


    suspend fun isOnboardingCompleted(): Boolean {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_ONBOARDING_COMPLETED] ?: false
        }.first()
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEY_ONBOARDING_COMPLETED] = completed
        }
    }
}
