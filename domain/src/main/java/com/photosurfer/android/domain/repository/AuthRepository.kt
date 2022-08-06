package com.photosurfer.android.domain.repository

interface AuthRepository {

    fun getFcmToken(tokenCallBack: () -> Unit)
}
