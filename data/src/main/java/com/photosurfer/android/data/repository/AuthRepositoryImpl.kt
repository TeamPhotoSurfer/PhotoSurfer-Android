package com.photosurfer.android.data.repository

import com.photosurfer.android.core.firebasemessaging.FirebaseTokenManager
import com.photosurfer.android.domain.repository.AuthRepository
import javax.inject.Inject

class
AuthRepositoryImpl @Inject constructor(
    private val firebaseTokenManager: FirebaseTokenManager
) : AuthRepository {
    override fun getFcmToken(tokenCallBack: (String) -> Unit) {
        firebaseTokenManager.getFirebaseToken {
            tokenCallBack(it)
        }
    }
}
