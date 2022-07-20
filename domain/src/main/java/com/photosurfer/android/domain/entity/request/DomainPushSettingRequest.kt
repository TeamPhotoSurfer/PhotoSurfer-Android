package com.photosurfer.android.domain.entity.request

data class DomainPushSettingRequest(
    val pushDate: String,
    val tagIds: MutableList<Int>,
    val memo: String
)
