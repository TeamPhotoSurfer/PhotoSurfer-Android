package com.photosurfer.android.domain.entity.request

import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo

data class SearchTagResult(
    val tags: List<TagInfo>,
    val photos: List<ThumbnailInfo>
)
