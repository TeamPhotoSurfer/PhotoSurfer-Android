package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.DetailImageResponse
import com.photosurfer.android.data.remote.model.response.NoDataResponse

interface RemoteImageDataSource {

    suspend fun getDetailImageInfo(photoId: Int): NetworkState<BaseResponse<DetailImageResponse>>

    suspend fun deleteImage(options: Map<String, Int>): NetworkState<NoDataResponse>
}
