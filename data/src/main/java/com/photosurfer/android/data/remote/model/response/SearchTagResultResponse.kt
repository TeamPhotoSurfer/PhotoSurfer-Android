package com.photosurfer.android.data.remote.model.response


import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import kotlinx.serialization.SerialName

data class SearchTagResultResponse(
    @SerialName("tags")
    val tags: List<TagInfo>,
    @SerialName("photos")
    val photos: List<ThumbnailInfo>
)
