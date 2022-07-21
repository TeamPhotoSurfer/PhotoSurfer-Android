package com.photosurfer.android.domain.entity

data class ThreeTagList(
    val recentTagList: MutableList<TagInfo>,
    val oftenTagList: MutableList<TagInfo>,
    val platformTagList: MutableList<TagInfo>
)
