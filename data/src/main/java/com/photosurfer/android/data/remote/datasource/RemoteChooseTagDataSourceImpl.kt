package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.ChooseTagResponse
import com.photosurfer.android.data.remote.service.ChooseTagService
import javax.inject.Inject

class RemoteChooseTagDataSourceImpl @Inject constructor(
    private val chooseTagService: ChooseTagService
) : RemoteChooseTagDataSource{
    override suspend fun postTag(
        chooseTagRequest: ChooseTagRequest
    ): NetworkState<BaseResponse<ChooseTagResponse>> =
        chooseTagService.postTag(chooseTagRequest)
}