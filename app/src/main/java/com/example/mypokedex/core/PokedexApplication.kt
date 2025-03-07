package com.example.mypokedex.core

import android.app.Application
import android.util.Log
import com.example.mypokedex.ui.notifications.NotificationsManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PokedexApplication : Application() {

    @Inject
    lateinit var notificationsManager: NotificationsManager

    override fun onCreate() {
        super.onCreate()
        Log.d("NotificationsTag", "onCreate from application")
        notificationsManager.createAllChannels()
    }
}