package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.LoginRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.LoginResponse

interface RemoteLoginDataSource {

    suspend fun postLogin(loginRequest: LoginRequest): NetworkState<BaseResponse<LoginResponse>>
}
