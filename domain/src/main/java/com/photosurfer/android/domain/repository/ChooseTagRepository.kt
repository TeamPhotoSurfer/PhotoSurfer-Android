package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest
import com.photosurfer.android.domain.entity.request.Tag

interface ChooseTagRepository {

    suspend fun postTag(
        domainChooseTagRequest: DomainChooseTagRequest
    ): Result<List<Tag>>
}