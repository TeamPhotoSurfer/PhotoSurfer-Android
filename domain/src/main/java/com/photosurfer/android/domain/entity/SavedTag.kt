package com.photosurfer.android.domain.entity

data class SavedTag(
    val bookmark_status: Boolean,
    val id: Int,
    val image_url: String,
    val name: String,
    val tag_type: String
)

