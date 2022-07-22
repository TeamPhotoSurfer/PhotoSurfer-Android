package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.*
import com.photosurfer.android.domain.entity.TagInfo

interface RemoteTagListDataSource {

    suspend fun getOftenSearchTagList(): NetworkState<BaseResponse<OftenSearchTagResponse>>

    suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>>

    suspend fun getSavedTagList(): NetworkState<BaseResponse<SavedTagResponse>>

    suspend fun putEditTagName(): NetworkState<BaseResponse<EditTagNameResponse>>

    suspend fun getAllTagList(): NetworkState<BaseResponse<MutableList<TagInfo>>>
}