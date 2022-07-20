package com.photosurfer.android.data.remote.model.response


import com.google.gson.annotations.SerializedName

data class PushSettingResponse(
    @SerializedName("memo")
    val memo: String,
    @SerializedName("pushDate")
    val pushDate: String,
    @SerializedName("tagIds")
    val tagIds: List<Int>
)
