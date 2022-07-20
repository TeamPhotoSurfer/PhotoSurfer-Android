package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.PushSettingRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.PushSettingResponse
import com.photosurfer.android.data.remote.service.PushSettingService
import javax.inject.Inject

class RemotePushSettingDataSourceImpl @Inject constructor(
    private val pushSettingService: PushSettingService
) : RemotePushSettingDataSource {

    override suspend fun postPushSetting(
        photoId: Int,
        pushSettingRequest: PushSettingRequest
    ): NetworkState<BaseResponse<PushSettingResponse>> =
        pushSettingService.postPushSetting(photoId, pushSettingRequest)
}
