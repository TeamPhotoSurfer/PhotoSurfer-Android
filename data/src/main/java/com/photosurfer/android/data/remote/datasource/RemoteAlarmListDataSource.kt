package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.MoreAlarmListResponse
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import com.photosurfer.android.domain.entity.AlarmElement

interface RemoteAlarmListDataSource {

    suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>>

    suspend fun getPassedAlarmList(): NetworkState<BaseResponse<MoreAlarmListResponse>>

    suspend fun getUpComingAlarmList(): NetworkState<BaseResponse<MoreAlarmListResponse>>

    suspend fun getSpecificAlarmInfo(pushId: Int): NetworkState<BaseResponse<AlarmElement>>
}
