package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.DetailImageResponse

interface RemoteImageDataSource {

    suspend fun getDetailImageInfo(photoId: Int): NetworkState<BaseResponse<DetailImageResponse>>
}
