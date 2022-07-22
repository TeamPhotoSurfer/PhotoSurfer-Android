package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.DetailImageResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailImageService {
    @GET("photo/detail/{photoId}")
    suspend fun getDetailImageInfo(
        @Path("photoID") photo: Int
    ): NetworkState<BaseResponse<DetailImageResponse>>
}
