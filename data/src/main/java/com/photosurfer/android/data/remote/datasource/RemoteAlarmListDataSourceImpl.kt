package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import com.photosurfer.android.data.remote.service.UrgentAlarmListService
import javax.inject.Inject

class RemoteAlarmListDataSourceImpl @Inject constructor(
    private val urgentAlarmListService: UrgentAlarmListService
) : RemoteAlarmListDataSource {
    override suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>> =
        urgentAlarmListService.getUrgentAlarmList()
}
