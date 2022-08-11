package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.LoginInfo
import com.photosurfer.android.domain.entity.request.DomainLoginRequest
import java.util.*

interface AuthRepository {

    fun getFcmToken(tokenCallBack: (String) -> Unit)

    suspend fun postLogin(domainLoginRequest: DomainLoginRequest): Result<LoginInfo>

    fun saveAccessToken(accessToken: String)

    fun getAccessToken(): String
}
