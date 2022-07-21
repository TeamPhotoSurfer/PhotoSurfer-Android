package com.photosurfer.android.data.remote.model.request


import com.photosurfer.android.domain.entity.TagNameType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class ChooseTagRequest(
    @SerialName("file")
    val file: File,
    @SerialName("tags")
    val tags: List<TagNameType>
)
