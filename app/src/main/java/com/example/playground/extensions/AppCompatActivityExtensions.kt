package com.example.playground.extensions

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.playground.NotificationsSampleTargetActivity
import com.example.playground.R


/**
 * Because you must create the notification channel before posting any notifications on
 * Android 8.0 and higher, you should execute this code as soon as your app starts.
 */
fun AppCompatActivity.startDefaultNotificationChannel(
    channels: List<Triple<String, String, String>> = listOf(Triple("MAIN", "The main app channel", "Used for the wake up time")),
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager: NotificationManager =
            this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        channels.forEach {
            val channel = NotificationChannel(
                it.first,
                it.second,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = it.third
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}



fun AppCompatActivity.showOrUpdateNotification(
    notificationId: Int,
    title: String,
    text: String,
    channelId: String = "MAIN",
    shouldLaunchIntent: Boolean = true,
    shouldIntentNewTask: Boolean = false
) {

    with(NotificationManagerCompat.from(this)) {

        val notificationIntent = Intent(applicationContext, NotificationsSampleTargetActivity::class.java).apply {
            if(shouldIntentNewTask) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            notificationIntent,
            0
        )

        val notification = buildNotificationManager(
            channelId = channelId
        ).apply {
            setContentTitle(title)
            setStyle(NotificationCompat.BigTextStyle().bigText(text))
            if(shouldLaunchIntent) setContentIntent(pendingIntent)
        }.build()

        notify(notificationId, notification)
    }
}

fun AppCompatActivity.buildNotificationManager(
    channelId: String,
    shouldAutoCancel: Boolean = true,
    shouldAlertOnlyOnce: Boolean = true,
    resIdSmallIcon: Int = R.drawable.ic_baseline_alarm,
    notificationPriority: Int = NotificationCompat.PRIORITY_DEFAULT
) : NotificationCompat.Builder {

    /**
     * Channel ID is is required for compatibility with Android 8.0 (API level 26) and higher,
     * but is ignored by older versions.
     */
    return NotificationCompat.Builder(this, channelId).apply {
        setSmallIcon(resIdSmallIcon)
        priority = notificationPriority
        setAutoCancel(shouldAutoCancel)
        setOnlyAlertOnce(shouldAlertOnlyOnce)
    }
}


fun AppCompatActivity.removeNotification(notificationId: Int) {
    with(NotificationManagerCompat.from(this)) {
        /** Cancel a specific notification by its ID. Also deletes ongoing notifications. */
        /** Cancel a specific notification by its ID. Also deletes ongoing notifications. */
        cancel(notificationId)
    }
}

fun AppCompatActivity.removeAllNotifications() {
    with(NotificationManagerCompat.from(this)) {
        /** Removes all of the notifications you previously issued. */
        /** Removes all of the notifications you previously issued. */
        cancelAll()
    }
}