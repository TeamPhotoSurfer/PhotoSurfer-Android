package com.photosurfer.android.domain.entity.request

import com.photosurfer.android.domain.entity.TagNameType
import java.io.File

data class DomainChooseTagRequest(
    val file: File,
    val tags: List<TagNameType>
)
