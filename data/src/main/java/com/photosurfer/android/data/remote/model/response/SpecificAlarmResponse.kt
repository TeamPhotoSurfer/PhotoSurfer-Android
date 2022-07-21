package com.photosurfer.android.data.remote.model.response

import com.photosurfer.android.domain.entity.TagInfo

data class SpecificAlarmResponse(
    val id: Int,
    val pushDate: String,
    val tags: List<TagInfo>,
    val memo: String
)
