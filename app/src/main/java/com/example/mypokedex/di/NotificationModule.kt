package com.example.mypokedex.di

import android.content.Context
import com.example.mypokedex.ui.notifications.NotificationsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun providesNotificationManager(@ApplicationContext context: Context) =
        NotificationsManager(context)
}