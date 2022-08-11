package com.photosurfer.android.data.local.datasource

interface LocalPreferenceUserDataSource {
    fun getAccessToken(): String

    fun saveAccessToken(accessToken: String)

    fun removeAccessToken()
}
