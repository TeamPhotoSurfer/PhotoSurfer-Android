package com.photosurfer.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class MoreAlarmListResponse(
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("push")
    val push: List<Push>
)
