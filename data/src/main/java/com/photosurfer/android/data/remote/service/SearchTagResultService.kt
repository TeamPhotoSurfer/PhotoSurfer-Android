package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.SearchTagResultResponse
import retrofit2.http.GET
import retrofit2.http.QueryName

interface SearchTagResultService {

    @GET("photo/search/?")
    suspend fun getSearchTagResultList(
        @QueryName options: List<Pair<String, Int>>
    ): NetworkState<BaseResponse<SearchTagResultResponse>>

}