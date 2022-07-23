package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.DetailImageInfo

interface ImageRepository {

    suspend fun getDetailImageInfo(photoId: Int): Result<DetailImageInfo>

    suspend fun deleteImage(options: Map<String, Int>): Result<String>
}
