package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.TagListResponse
import com.photosurfer.android.data.remote.model.response.OftenSearchTagResponse
import com.photosurfer.android.data.remote.model.response.SavedTagResponse

interface RemoteTagListDataSource {

    suspend fun getOftenSearchTagList(): NetworkState<BaseResponse<OftenSearchTagResponse>>

    suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>>

    suspend fun getSavedTagList(): NetworkState<BaseResponse<SavedTagResponse>>
}