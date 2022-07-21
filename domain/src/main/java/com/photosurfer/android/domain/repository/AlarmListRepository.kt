package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.AlarmInfo

interface AlarmListRepository {
    suspend fun getUrgentAlarmList(): Result<AlarmInfo>
}
