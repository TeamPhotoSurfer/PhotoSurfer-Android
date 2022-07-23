package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.SearchTagResultResponse

interface RemoteSearchTagResultDataSource {

    suspend fun getSearchTagResultList(options: List<Int>): NetworkState<BaseResponse<SearchTagResultResponse>>

}