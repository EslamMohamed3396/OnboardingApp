package com.example.onboardingapp.di

import com.example.onboardingapp.data.local.UserDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object DataStoreModule {
//
//    @Provides
//    @Singleton
//    fun provideDataStore(
//        userDataStore: UserDataStore
//    ): UserDataStore {
//        return userDataStore
//    }
//}
