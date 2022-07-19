package com.photosurfer.android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PhotoSurferMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        // 서버에게 토큰 새로 보내주는 코드
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // 채널 생성
        createNotificationChannel()

        if (remoteMessage.data.isNotEmpty()) {
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            val imageUrl = remoteMessage.data["imageUrl"]
        }
    }

    private fun createNotification(title: String, body: String, imageurl: String): Notification {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0 /* Request code */,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)// 아이콘 보여주기
            .setContentTitle(title) // 메세지 에서 받은 타이틀 활용
            .setContentText(body) // 메세지 에서 받은 메세지 활용
            .setPriority(NotificationCompat.PRIORITY_HIGH) // 오레오 이하 버전 에서는 지정 필요
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // 알림 클릭시 자동 제거
            .setSound(defaultSoundUri)
    }

    private fun createNotificationChannel() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        /** Oreo Version 이하일때 처리 하는 코드 */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                ).apply { description = CHANNEL_DESCRIPTION }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        const val CHANNEL_ID = "PhotoSurfer_channel"
        const val CHANNEL_NAME = "com.photosurfer.android"
        const val CHANNEL_DESCRIPTION = "photoSurfer 채널"
    }
}
