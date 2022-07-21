package com.photosurfer.android.data.remote.model.response


import com.photosurfer.android.domain.entity.TagInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagListResponse(
    @SerialName("often")
    val often: Often,
    @SerialName("recent")
    val recent: Recent,
    @SerialName("platform")
    val platform: Platform
)

data class Often(
    @SerialName("tags")
    val tags: List<TagInfo>
)

data class Recent(
    @SerialName("tags")
    val tags: List<TagInfo>
)

data class Platform(
    @SerialName("tags")
    val tags: List<TagInfo>
)