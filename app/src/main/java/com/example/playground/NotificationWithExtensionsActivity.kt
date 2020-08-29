package com.example.playground

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.extensions.removeAllNotifications
import com.example.playground.extensions.removeNotification
import com.example.playground.extensions.showOrUpdateNotification
import com.example.playground.extensions.startDefaultNotificationChannel
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



