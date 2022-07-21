package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.ChooseTagResponse

interface RemoteChooseTagDataSource {

    suspend fun postTag(
        chooseTagRequest: ChooseTagRequest
    ): NetworkState<BaseResponse<ChooseTagResponse>>
}