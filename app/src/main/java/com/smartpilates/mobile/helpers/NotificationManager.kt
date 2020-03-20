package com.smartpilates.mobile.helpers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.smartpilates.mobile.R
import com.smartpilates.mobile.SplashScreen

class NotificationManager {

    companion object {

        private const val NOTIF_CHANNEL_ID="SMART_PLATES_APP_CHANNEL_ID"
        private const val CHANNEL_NAME="SMART_PLATES_APP_NOTIF_CHANNEL_NAME"
        private const val CHANNEL_DES="SMART_PLATES_APP_CHANNEL_DESC"

        fun showNotification(context: Context, notifID:Int, bigText:String, contentTitle:String){

            val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val intent = Intent(context, SplashScreen::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)


            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = CHANNEL_NAME
                val descriptionText = CHANNEL_DES
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(NOTIF_CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system

                notificationManager.createNotificationChannel(channel)
            }


            val notificationBuilder= NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
                .setContentTitle(contentTitle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(alarmSound)

            notificationManager.notify(notifID,notificationBuilder.build())

        }
    }
}