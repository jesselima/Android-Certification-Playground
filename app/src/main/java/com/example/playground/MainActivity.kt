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
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Read more about Notification:
 * {@link https://developer.android.com/training/notify-user/build-notification }
 * {@link https://developer.android.com/guide/topics/ui/notifiers/notifications }
 */
private const val channelId = "main"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Before you can deliver the notification on Android 8.0 and higher, you must register
         * your app's notification channel with the system by passing an instance of
         * NotificationChannel to createNotificationChannel(). So the following code is blocked by
         * a condition on the SDK_INT version:
         */
        createNotificationChannel()

        /**
         * Channel ID is is required for compatibility with Android 8.0 (API level 26) and higher,
         * but is ignored by older versions.
         */
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        notificationBuilder.setSmallIcon(R.drawable.ic_baseline_alarm)
        notificationBuilder.setContentTitle("Hey! Wake up!")
        /**
         * Notice that the NotificationChannel constructor requires an importance (see
         * createNotificationChannel method), using one of the constants from the
         * NotificationManager class. This parameter determines how to
         * interrupt the user for any notification that belongs to this channel—though you must
         * also set the priority with setPriority() to support Android 7.1 and lower (as shown
         * above).
         * https://developer.android.com/training/notify-user/channels#importance
         */
        notificationBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        /**
         *  Enable an expandable notification by adding a style
         *  More about expandable https://developer.android.com/training/notify-user/expanded
        */
        notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
            .bigText("It is 10:00 a.m What are you still in bed? Go to work lazy boy!"))

        /**
         * Every notification should respond to a tap, usually to open an activity in your app that
         * corresponds to the notification. To do so, you must specify a content intent defined
         * with a PendingIntent object and pass it to setContentIntent().
         */
        val intent = Intent(this, NotificationsActivity::class.java).apply {
            /**
             * The setFlags() method shown above helps preserve the user's expected navigation
             * experience after they open your app via the notification. But whether you want to
             * use that depends on what type of activity you're starting, which may be one of the
             * following:
             *
             * - An activity that exists exclusively for responses to the notification. There's no
             *  reason the user would navigate to this activity during normal app use, so the
             *  activity starts a new task instead of being added to your app's existing task and
             *  back stack (https://developer.android.com/guide/components/activities/tasks-and-back-stack).
             *  This is the type of intent created in the sample above.
             *
             * - An activity that exists in your app's regular app flow. In this case, starting the
             * activity should create a back stack so that the user's expectations for the Back and
             * Up buttons is preserved (https://developer.android.com/design/patterns/navigation).
             *
             * See also:
             * https://developer.android.com/training/notify-user/navigation
             */
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        /** Then set the content intent to the notification */
        notificationBuilder.setContentIntent(pendingIntent)

        /** setAutoCancel(), which automatically removes the notification when the user taps it. */
        notificationBuilder.setAutoCancel(true)


        /**
         * You can optionally call setOnlyAlertOnce() so your notification interrupts the user
         * (with sound, vibration, or visual clues) only the first time the notification appears
         * and not for later updates.
         */
        notificationBuilder.setOnlyAlertOnce(true)

        /** The notification ID is a unique int for each notification
         * Remember to save the notification ID that you pass to NotificationManagerCompat.notify()
         * because you'll need it later if you want to update or remove the notification.
         * */
        val dummyNotificationIdOne = 4847584
        val dummyNotificationIdTwo = 7654322

        buttonShowNotificationOne.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notificationBuilder.setContentTitle("Hey! Wake up!")
                notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText("It is 10:00 a.m What are you still in bed? Go to work lazy boy!"))
                notify(dummyNotificationIdOne, notificationBuilder.build())
            }
        }

        buttonShowNotificationTwo.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notificationBuilder.setContentTitle("I am back!")
                notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText("Just to disturb you. Wake up! :P"))
                notify(dummyNotificationIdTwo, notificationBuilder.build())
            }
        }

        buttonUpdateNotificationOne.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                notificationBuilder.setContentTitle("Ooops! Go back to sleep!")
                notificationBuilder.setStyle(NotificationCompat.BigTextStyle()
                    .bigText("I was wrong. Time zone problems :P!"))
                notify(dummyNotificationIdOne, notificationBuilder.build())
            }
        }

        /**
         *  Notifications remain visible until one of the following happens:
         *
         *  - The user dismisses the notification.
         *  - The user clicks the notification, and you called setAutoCancel() when you created
         *      the notification.
         *  - You call cancel() for a specific notification ID. This method also deletes ongoing
         *      notifications.
         *  - You call cancelAll(), which removes all of the notifications you previously issued.
         *  - If you set a timeout when creating a notification using setTimeoutAfter(), the system
         *      cancels the notification after the specified duration elapses. If required, you can
         *          cancel a notification before the specified timeout duration elapses.
         */
        buttonRemoveNotificationOne.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                /** Cancel a specific notification by its ID. Also deletes ongoing notifications. */
                cancel(dummyNotificationIdOne)
            }
        }

        buttonRemoveAllNotifications.setOnClickListener {
            with(NotificationManagerCompat.from(this)) {
                /** Removes all of the notifications you previously issued. */
                cancelAll()
            }
        }
    }

    /**
     * Because you must create the notification channel before posting any notifications on
     * Android 8.0 and higher, you should execute this code as soon as your app starts.
     */
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /**
             * Create the notification channel
             */
            val channelName = "My super alarm channel"
            val channelDescription = "The main alarm channel"
            val channelImportance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, channelImportance).apply {
                description = channelDescription
            }

            /**
             * Register the channel with the system; you can't change the importance
             * or other notification behaviors after this
             */
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
