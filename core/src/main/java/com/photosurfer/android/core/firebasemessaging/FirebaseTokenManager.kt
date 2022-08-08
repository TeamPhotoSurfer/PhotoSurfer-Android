package com.photosurfer.android.core.firebasemessaging

interface FirebaseTokenManager {
    fun getFirebaseToken(tokenCallBack: (String) -> Unit)
}

