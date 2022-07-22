package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.SearchTagResultResponse
import com.photosurfer.android.data.remote.service.SearchTagResultService
import javax.inject.Inject

class RemoteSearchTagResultDataSourceImpl @Inject constructor(
    private val searchTagService: SearchTagResultService
) : RemoteSearchTagResultDataSource {

    override suspend fun getSearchTagResultList(
        options: List<Pair<String, Int>>
    ): NetworkState<BaseResponse<SearchTagResultResponse>> =
        searchTagService.getSearchTagResultList(options)

}