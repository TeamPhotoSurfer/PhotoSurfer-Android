package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.request.DomainPushSettingRequest

interface PushSettingRepository {

    suspend fun postPushSetting(
        photoId: Int,
        domainPushSettingRequest: DomainPushSettingRequest
    ): Result<String>
}
