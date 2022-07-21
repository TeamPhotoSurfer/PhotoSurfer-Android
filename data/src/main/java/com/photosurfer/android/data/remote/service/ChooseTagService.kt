package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.ChooseTagResponse
import com.photosurfer.android.data.remote.model.response.TagListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST

interface ChooseTagService {
    @Multipart
    @POST("photo/")
    suspend fun postTag(
        @Body body: ChooseTagRequest
    ): NetworkState<BaseResponse<ChooseTagResponse>>

    @GET("tag/main")
    suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>>
}