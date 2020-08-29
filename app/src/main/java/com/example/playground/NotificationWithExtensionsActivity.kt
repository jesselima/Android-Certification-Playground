package com.example.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_notification_with_extensions.*

class NotificationWithExtensionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_with_extensions)

        startDefaultNotificationChannel()

        val idNotificationOne = 4337584
        val idNotificationTwo = 7334322

        buttonShowNotificationOneUsingExtensions.setOnClickListener {
            val notificationTitle = "Hey! Wake up!"
            val bigText = "It is 10:00 a.m What are you still in bed? Go to work lazy boy!"
            showOrUpdateNotification(
                notificationId = idNotificationOne,
                title = notificationTitle,
                text = bigText
            )
        }

        buttonShowNotificationTwoUsingExtensions.setOnClickListener {
            val notificationTitle = "I am back!"
            val bigText = "Just to disturb you. Wake up! :P"
            showOrUpdateNotification(
                notificationId = idNotificationTwo,
                title = notificationTitle,
                text = bigText
            )
        }

        buttonUpdateNotificationOneUsingExtensions.setOnClickListener {
            val notificationTitle = "Ooops! Go back to sleep!"
            val bigText = "I was wrong. Time zone problems :P!"
            showOrUpdateNotification(
                notificationId = idNotificationOne,
                title = notificationTitle,
                text = bigText
            )
        }

        buttonRemoveNotificationOneUsingExtensions.setOnClickListener {
            removeNotification(idNotificationOne)
        }

        buttonRemoveAllNotificationsUsingExtensions.setOnClickListener {
            removeAllNotifications()
        }

        buttonMainActivity.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}




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
        cancel(notificationId)
    }
}

fun AppCompatActivity.removeAllNotifications() {
    with(NotificationManagerCompat.from(this)) {
        /** Removes all of the notifications you previously issued. */
        cancelAll()
    }
}