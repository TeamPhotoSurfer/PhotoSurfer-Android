package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.request.SearchTagResult

interface PhotoRepository {

    suspend fun getPhotoListByTag(options: Map<String, Int>): Result<SearchTagResult>

}