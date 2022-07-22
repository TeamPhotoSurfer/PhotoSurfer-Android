package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.TagInfo

interface TagListRepository {

    suspend fun getOftenSearchTagList(): Result<List<TagInfo>>


}