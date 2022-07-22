package com.photosurfer.android.domain.entity

data class SavedTagList(
    val bookmarked: MutableList<SavedTag>,
    val notBookmarked: MutableList<SavedTag>
)
