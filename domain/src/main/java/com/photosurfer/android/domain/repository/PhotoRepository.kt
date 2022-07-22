package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.request.SearchTagResult

interface PhotoRepository {

    suspend fun getPhotoListByTag(options: List<Int>): Result<SearchTagResult>

}