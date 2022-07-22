package com.photosurfer.android.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.photosurfer.android.domain.entity.TagInfo

data class DetailImageResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("push")
    val push: Int,
    @SerializedName("tags")
    val tags: List<TagInfo>
)
