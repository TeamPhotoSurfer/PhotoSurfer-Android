package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.*
import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest

interface ChooseTagRepository {

    suspend fun postTag(
        domainChooseTagRequest: DomainChooseTagRequest
    ): Result<List<TagIdNameType>>

    suspend fun getTagList(): Result<ThreeTagList>

    suspend fun getSavedTagList(): Result<SavedTagList>

    suspend fun putNewTagName(): Result<String>

    suspend fun getAllTagList(): Result<MutableList<TagInfo>>
}