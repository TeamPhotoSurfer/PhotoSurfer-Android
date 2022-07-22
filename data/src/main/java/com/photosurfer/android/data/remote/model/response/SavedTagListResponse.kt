package com.photosurfer.android.data.remote.model.response


import com.photosurfer.android.domain.entity.SavedTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SavedTagResponse(
    @SerialName("bookmarked")
    val bookmarked: Bookmarked,
    @SerialName("notBookmarked")
    val notBookmarked: NotBookmarked
) {
    @Serializable
    data class Bookmarked(
        @SerialName("tags")
        val tags: List<SavedTag>
    )

    @Serializable
    data class NotBookmarked(
        @SerialName("tags")
        val tags: List<SavedTag>
    )
}
