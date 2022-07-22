package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.*
import com.photosurfer.android.data.remote.service.ChooseTagService
import com.photosurfer.android.data.remote.service.OftenSearchTagService
import com.photosurfer.android.domain.entity.TagInfo
import javax.inject.Inject

class RemoteTagListDataSourceImpl @Inject constructor(
    private val oftenSearchTagService: OftenSearchTagService,
    private val chooseTagListService: ChooseTagService
) : RemoteTagListDataSource {

    override suspend fun getOftenSearchTagList(): NetworkState<BaseResponse<OftenSearchTagResponse>> =
        oftenSearchTagService.getOftenSearchTagService()

    override suspend fun getTagList(): NetworkState<BaseResponse<TagListResponse>> =
        chooseTagListService.getTagList()

    override suspend fun getSavedTagList(): NetworkState<BaseResponse<SavedTagResponse>> =
        chooseTagListService.getSavedTagList()

    override suspend fun putEditTagName(): NetworkState<BaseResponse<EditTagNameResponse>> =
        chooseTagListService.putSavedTagName()

    override suspend fun getAllTagList(): NetworkState<BaseResponse<MutableList<TagInfo>>> =
        chooseTagListService.getAllTagList()


}