package com.example.mypokedex.ui.notifications

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.app.NotificationCompat
import com.example.mypokedex.ui.R

@OptIn(ExperimentalMaterial3Api::class)
enum class NotificationType(
    val id: Int,
    val title: String,
    val description: String,
    val icon: Int,
    val priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    val channel: ChannelType
) {
    FIRST_NOTIFICATION(
        1,
        "First Notification",
        "FirstDescription",
        R.drawable.baseline_sort_by_alpha_24,
        channel = ChannelType.FIRST_CHANNEL
    )
}
