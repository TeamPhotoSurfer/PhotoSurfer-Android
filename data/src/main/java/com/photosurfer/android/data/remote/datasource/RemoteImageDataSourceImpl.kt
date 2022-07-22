package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.DetailImageResponse
import com.photosurfer.android.data.remote.service.DetailImageService
import javax.inject.Inject

class RemoteImageDataSourceImpl @Inject constructor(
    private val detailImageService: DetailImageService
) : RemoteImageDataSource {

    override suspend fun getDetailImageInfo(photoId: Int): NetworkState<BaseResponse<DetailImageResponse>> =
        detailImageService.getDetailImageInfo(photoId)
}
