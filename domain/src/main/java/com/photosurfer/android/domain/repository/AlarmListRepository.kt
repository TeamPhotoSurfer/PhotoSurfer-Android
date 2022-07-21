package com.photosurfer.android.domain.repository

import com.photosurfer.android.domain.entity.AlarmElement
import com.photosurfer.android.domain.entity.AlarmInfo

interface AlarmListRepository {
    suspend fun getUrgentAlarmList(): Result<AlarmInfo>

    suspend fun getPassedAlarmList(): Result<List<AlarmElement>>

    suspend fun getUpComingAlarmList(): Result<List<AlarmElement>>

    suspend fun getSpecificAlarmInfo(pushId: Int): Result<AlarmElement>
}
