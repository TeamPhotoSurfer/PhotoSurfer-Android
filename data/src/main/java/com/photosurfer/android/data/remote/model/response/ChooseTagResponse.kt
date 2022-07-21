package com.photosurfer.android.data.remote.model.response


import com.photosurfer.android.domain.entity.TagIdNameType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChooseTagResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("imageURL")
    val imageURL: String,
    @SerialName("tag")
    val tag: List<TagIdNameType>
)
