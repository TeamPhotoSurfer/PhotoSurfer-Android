package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.PushSettingRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.PushSettingResponse

interface RemotePushSettingDataSource {

    suspend fun postPushSetting(
        photoId: Int,
        pushSettingRequest: PushSettingRequest
    ): NetworkState<BaseResponse<PushSettingResponse>>
}
