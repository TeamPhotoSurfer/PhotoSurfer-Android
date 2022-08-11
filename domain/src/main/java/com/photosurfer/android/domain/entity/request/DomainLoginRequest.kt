package com.photosurfer.android.domain.entity.request

data class DomainLoginRequest(
    val socialToken: String,
    val socialType: String,
    val fcm: String
)
