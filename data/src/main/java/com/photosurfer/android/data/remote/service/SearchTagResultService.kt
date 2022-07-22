package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.SearchTagResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTagResultService {

    @GET("photo/search/?")
    suspend fun getSearchTagResultList(
        @Query("id") options: List<Int>
    ): NetworkState<BaseResponse<SearchTagResultResponse>>

}