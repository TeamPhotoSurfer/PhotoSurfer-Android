package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.DetailImageResponse
import com.photosurfer.android.data.remote.model.response.NoDataResponse
import com.photosurfer.android.data.remote.service.DeleteImageService
import com.photosurfer.android.data.remote.service.DetailImageService
import javax.inject.Inject

class RemoteImageDataSourceImpl @Inject constructor(
    private val detailImageService: DetailImageService,
    private val deleteImageService: DeleteImageService
) : RemoteImageDataSource {

    override suspend fun getDetailImageInfo(photoId: Int): NetworkState<BaseResponse<DetailImageResponse>> =
        detailImageService.getDetailImageInfo(photoId)

    override suspend fun deleteImage(options: Map<String, Int>): NetworkState<NoDataResponse> =
        deleteImageService.deleteImage(options)
}
