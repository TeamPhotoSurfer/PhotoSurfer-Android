package com.photosurfer.android.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class PushSettingRequest(
    @SerializedName("pushDate")
    val pushDate: String,
    @SerializedName("tagIds")
    val tagIds: List<Int>,
    @SerializedName("memo")
    val memo: String
)
