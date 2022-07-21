package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.OftenSearchTagResponse
import retrofit2.http.GET

interface OftenSearchTagService {
    @GET("tag/search")
    suspend fun getOftenSearchTagService(): NetworkState<BaseResponse<OftenSearchTagResponse>>
}