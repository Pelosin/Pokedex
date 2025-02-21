package com.example.mypokedex.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.mypokedex.data.local.memory.repository.IsFirstOpenRepository
import com.example.mypokedex.data.utils.SharedPreferencesHelper
import com.example.mypokedex.domain.repository.IIsFirstOpenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Singleton
    @Provides
    fun providesSharedPrefsHelper(@ApplicationContext context: Context)
            : SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun providesIsFirstOpenRepository(sharedPreferencesHelper: SharedPreferencesHelper):
            IIsFirstOpenRepository = IsFirstOpenRepository(sharedPreferencesHelper)
}