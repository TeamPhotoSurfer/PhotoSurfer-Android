package com.photosurfer.android.domain.entity

data class DetailImageInfo(
    val photoId: Int,
    val imageUrl: String,
    val tagList: List<TagInfo>
)
