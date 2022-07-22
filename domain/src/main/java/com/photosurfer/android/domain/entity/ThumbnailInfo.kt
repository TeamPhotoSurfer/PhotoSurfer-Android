package com.photosurfer.android.domain.entity

data class ThumbnailInfo(
    val id: Int,
    val imageUrl: String,
    var isChecked: Boolean = false
)
