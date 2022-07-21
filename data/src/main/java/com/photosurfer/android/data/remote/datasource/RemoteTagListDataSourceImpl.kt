package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.TagListResponse
import com.photosurfer.android.data.remote.service.ChooseTagService
import javax.inject.Inject

class RemoteTagListDataSourceImpl @Inject constructor(
    private val chooseTagListService: ChooseTagService
): RemoteTagListDataSource {
    override suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>> =
        chooseTagListService.getTagList()
}