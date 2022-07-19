package com.photosurfer.android

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PhotoSurferMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        // 서버에게 토큰 새로 보내주는 코드
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
    }
}
