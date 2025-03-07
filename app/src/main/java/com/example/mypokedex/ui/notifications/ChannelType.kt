package com.example.mypokedex.ui.notifications

import android.app.NotificationManager

enum class ChannelType(
    val channelId: String,
    val channelName: String,
    val description: String,
    val importance: Int
) {
    FIRST_CHANNEL(
        channelId = "ChannelId",
        channelName = "Channel Name",
        description = "Channel Description",
        importance = NotificationManager.IMPORTANCE_DEFAULT
    )
}