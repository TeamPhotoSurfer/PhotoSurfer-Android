package com.photosurfer.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class UrgentAlarmListResponse(
    @SerializedName("comingCount")
    val comingCount: Int,
    @SerializedName("lastCount")
    val lastCount: Int,
    @SerializedName("today")
    val today: Today,
    @SerializedName("todayTomorrowCount")
    val todayTomorrowCount: Int,
    @SerializedName("tomorrow")
    val tomorrow: Tomorrow
)

data class Today(
    @SerializedName("push")
    val push: List<Push>
)

data class Push(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("memo")
    val memo: String,
    @SerializedName("photoId")
    val photoId: Int,
    @SerializedName("pushDate")
    val pushDate: String,
    @SerializedName("tags")
    val tags: List<String>
)

data class Tomorrow(
    @SerializedName("push")
    val push: List<Push>
)


