package com.photosurfer.android.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("socialToken")
    val socialToken: String,
    @SerializedName("socialType")
    val socialType: String,
    @SerializedName("fcm")
    val fcm: String
)
