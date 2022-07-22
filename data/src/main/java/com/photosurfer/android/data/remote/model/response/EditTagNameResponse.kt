package com.photosurfer.android.data.remote.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditTagNameResponse(
    @SerialName("name")
    val name: String
)