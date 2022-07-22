package com.photosurfer.android.data.remote.mapper

import com.photosurfer.android.data.remote.model.response.DetailImageResponse
import com.photosurfer.android.domain.entity.DetailImageInfo
import javax.inject.Inject

class ImageMapper @Inject constructor() {

    fun toDetailImageInfo(detailImageResponse: DetailImageResponse): DetailImageInfo =
        DetailImageInfo(
            photoId = detailImageResponse.id,
            imageUrl = detailImageResponse.imageUrl,
            tagList = detailImageResponse.tags
        )
}
