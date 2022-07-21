package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.TagListResponse
import com.photosurfer.android.data.remote.service.ChooseTagService
import com.photosurfer.android.data.remote.model.response.OftenSearchTagResponse
import com.photosurfer.android.data.remote.service.OftenSearchTagService
import javax.inject.Inject

class RemoteTagListDataSourceImpl @Inject constructor(
    private val oftenSearchTagService: OftenSearchTagService,
    private val chooseTagListService: ChooseTagService
) : RemoteTagListDataSource {

    override suspend fun getOftenSearchTagList(): NetworkState<BaseResponse<OftenSearchTagResponse>> =
        oftenSearchTagService.getOftenSearchTagService()

    override suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>> =
        chooseTagListService.getTagList()
        
}