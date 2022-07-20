package com.photosurfer.android.domain.entity.request

data class DomainPushSettingRequest(
    val pushDate: String,
    val tagIds: List<Int>,
    val memo: String
)
