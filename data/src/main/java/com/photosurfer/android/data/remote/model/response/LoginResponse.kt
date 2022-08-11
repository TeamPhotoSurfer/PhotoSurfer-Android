package com.photosurfer.android.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accesstoken")
    val accesstoken: String,
    @SerializedName("checkUser")
    val checkUser: CheckUser
)

data class CheckUser(
    @SerializedName("createdAt")
    val createdAt: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("fcmToken")
    val fcmToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isDeleted")
    val isDeleted: Boolean,
    @SerializedName("name")
    val name: Any,
    @SerializedName("push")
    val isPushActivated: Boolean,
    @SerializedName("socialType")
    val socialType: String,
    @SerializedName("updatedAt")
    val updatedAt: Any
)
