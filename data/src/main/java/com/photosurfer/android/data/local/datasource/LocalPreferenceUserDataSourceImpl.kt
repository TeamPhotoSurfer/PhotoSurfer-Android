package com.photosurfer.android.data.local.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LocalPreferenceUserDataSourceImpl @Inject constructor(
    private val localPreferences: SharedPreferences
) : LocalPreferenceUserDataSource {
    override fun getAccessToken(): String =
        localPreferences.getString(PHOTO_SURFER_ACCESS_TOKEN, "") ?: ""

    override fun saveAccessToken(accessToken: String) {
        localPreferences.edit { putString(PHOTO_SURFER_ACCESS_TOKEN, accessToken) }
    }

    override fun removeAccessToken() {
        localPreferences.edit { remove(PHOTO_SURFER_ACCESS_TOKEN) }
    }

    companion object {
        const val PHOTO_SURFER_ACCESS_TOKEN = "PHOTO_SURFER_ACCESS_TOKEN"
    }
}
