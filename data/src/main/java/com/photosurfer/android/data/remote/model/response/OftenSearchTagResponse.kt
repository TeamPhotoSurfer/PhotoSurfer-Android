package com.photosurfer.android.data.remote.model.response


import com.photosurfer.android.domain.entity.TagInfo
import kotlinx.serialization.SerialName

data class OftenSearchTagResponse(
    @SerialName("tags")
    val tags: List<TagInfo>
)