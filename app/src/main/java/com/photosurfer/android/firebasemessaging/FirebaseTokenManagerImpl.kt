package com.photosurfer.android.firebasemessaging

import com.google.firebase.messaging.FirebaseMessaging
import com.photosurfer.android.core.firebasemessaging.FirebaseTokenManager
import javax.inject.Inject

class FirebaseTokenManagerImpl @Inject constructor() : FirebaseTokenManager {
    override fun getFirebaseToken(tokenCallBack: () -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tokenCallBack()
            }
        }
    }
}
