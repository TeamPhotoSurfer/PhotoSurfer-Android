package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.AlarmInfo

interface UrgentAlarmListRepository {
    suspend fun getUrgentAlarmList(): Result<AlarmInfo>
}
