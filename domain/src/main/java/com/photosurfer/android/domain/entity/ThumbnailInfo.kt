package com.photosurfer.android.domain.entity

data class ThumbnailInfo(
    val id: Int,
    val imageURL: String,
    var isChecked: Boolean = false
)
