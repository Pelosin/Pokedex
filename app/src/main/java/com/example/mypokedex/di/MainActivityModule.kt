package com.example.mypokedex.di

import com.example.mypokedex.ui.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.compose.material3.ExperimentalMaterial3Api
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainActivityModule {

    @OptIn(ExperimentalMaterial3Api::class)

    @Provides
    @Singleton
    fun provideMainActivityClass(): Class<*> = MainActivity::class.java
}
