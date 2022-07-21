package com.photosurfer.android.data.remote.mapper

import com.photosurfer.android.data.remote.model.request.Tag
import javax.inject.Inject

class TagMapper @Inject constructor() {

    fun toDomainTag(tag: com.photosurfer.android.domain.entity.request.Tag):Tag =
        Tag(name = tag.name, type = tag.type)

    fun toDataTag(tag: Tag):com.photosurfer.android.domain.entity.request.Tag =
        com.photosurfer.android.domain.entity.request.Tag(name = tag.name, type = tag.type)
}