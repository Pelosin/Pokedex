package com.example.mypokedex.ui.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
class NotificationsManager @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
    val notificationManager: NotificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotification(notificationType: NotificationType) {
        val intent = notificationType.classToNavigate?.let {
            Intent(applicationContext, it).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val notification =
            NotificationCompat.Builder(applicationContext, notificationType.channel.channelId)
                .setSmallIcon(notificationType.icon)
                .setContentTitle(notificationType.title)
                .setContentText(notificationType.description)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(notificationType.description)
                )
                .setPriority(notificationType.priority)
                .setContentIntent(pendingIntent)
                .build()

        notificationManager.notify(notificationType.id, notification)
    }

    private fun createNotificationChannel(channel: ChannelType) {
        NotificationChannel(
            channel.channelId, channel.channelName, channel.importance
        ).apply {
            description = channel.description
        }.let { notificationManager.createNotificationChannel(it) }
    }

    fun createAllChannels() {
        ChannelType.entries.forEach { channelType ->
            this.createNotificationChannel(channelType)
        }
    }


}