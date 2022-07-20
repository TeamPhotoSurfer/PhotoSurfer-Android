package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.PushSettingRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.PushSettingResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PushSettingService {
    @POST("photo/push/{photoId}")
    suspend fun pushSetting(
        @Path("photoId") photoId: Int,
        @Body body: PushSettingRequest
    ): NetworkState<BaseResponse<PushSettingResponse>>
}
