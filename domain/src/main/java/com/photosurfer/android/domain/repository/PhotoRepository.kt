package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.request.SearchTagResult

interface PhotoRepository {

    suspend fun getPhotoListByTag(options: List<Pair<String, Int>>): Result<SearchTagResult>

}