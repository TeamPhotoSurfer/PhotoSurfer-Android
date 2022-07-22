package com.photosurfer.android.data.remote.service

import androidx.lifecycle.ViewModelProvider
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.data.remote.model.response.*
import com.photosurfer.android.domain.entity.TagInfo
import retrofit2.http.*

interface ChooseTagService {
    @Multipart
    @POST("photo/")
    suspend fun postTag(
        @Body body: ChooseTagRequest
    ): NetworkState<BaseResponse<ChooseTagResponse>>

    /*
    suspend fun postTag(
        @Part image: MultipartBody.Part?,
        @Part("contents") contents: List<TagNameType>
    ): NetworkState<BaseResponse<ChooseTagResponse>>
     */

    @GET("tag/main")
    suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>>

    @GET("tag")
    suspend fun getSavedTagList(): NetworkState<BaseResponse<SavedTagResponse>>

    @PUT("/tag/{tagId}")
    suspend fun putSavedTagName() : NetworkState<BaseResponse<EditTagNameResponse>>

    @GET("photo/tag")
    suspend fun getAllTagList(): NetworkState<BaseResponse<MutableList<TagInfo>>>
}