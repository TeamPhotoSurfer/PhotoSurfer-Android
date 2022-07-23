package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.NoDataResponse
import retrofit2.http.PUT
import retrofit2.http.QueryMap

interface DeleteImageService {
    @PUT("photo")
    suspend fun deleteImage(
        @QueryMap options: Map<String, Int>
    ): NetworkState<NoDataResponse>
}
