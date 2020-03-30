package com.ngerancang.kiwariandroidtest.service

import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ngerancang.kiwariandroidtest.ui.ChatActivity
import com.ngerancang.kiwariandroidtest.utilities.Constant


class MyFirebaseMessageService : FirebaseMessagingService() {

    var friendName : String ?= ""
    var friendId : String ?= ""

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //super.onMessageReceived(remoteMessage)


        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        println("masuk messege received 1 ${body}")

        if(remoteMessage.data.isNotEmpty()){
            friendName = remoteMessage.data[Constant.EXTRA_NAME_FRIEND]
            friendId = remoteMessage.data[Constant.EXTRA_UID_FRIEND]

            println("masuk messege friendName ${friendName}")
            println("masuk messege friendId ${friendId}")

        }


        sendNotification(
            title!!,
            body!!
        )

    }

    private fun sendNotification(messageTitle: String, messageBody: String) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.apply {
            this.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            this.putExtra(Constant.EXTRA_NAME_FRIEND, friendName)
            this.putExtra(Constant.EXTRA_UID_FRIEND, friendId)
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0 /* request code */,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pattern = longArrayOf(500, 500, 500, 500, 500)

        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_menu_add)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setVibrate(pattern)
            .setLights(Color.BLUE, 1, 1)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent) as NotificationCompat.Builder

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)


    }

}