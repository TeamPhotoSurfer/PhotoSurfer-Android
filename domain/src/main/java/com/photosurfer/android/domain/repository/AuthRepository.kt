package com.photosurfer.android.domain.repository

import java.util.*

interface AuthRepository {

    fun getFcmToken(tokenCallBack: (String) -> Unit)
}
