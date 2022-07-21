package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.Push
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import com.photosurfer.android.data.remote.service.PassedAlarmListService
import com.photosurfer.android.data.remote.service.UpComingAlarmListService
import com.photosurfer.android.data.remote.service.UrgentAlarmListService
import javax.inject.Inject

class RemoteAlarmListDataSourceImpl @Inject constructor(
    private val urgentAlarmListService: UrgentAlarmListService,
    private val passedAlarmListService: PassedAlarmListService,
    private val upComingAlarmListService: UpComingAlarmListService
) : RemoteAlarmListDataSource {
    override suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>> =
        urgentAlarmListService.getUrgentAlarmList()

    override suspend fun getPassedAlarmList(): NetworkState<BaseResponse<List<Push>>> =
        passedAlarmListService.getPassedAlarmList()

    override suspend fun getUpComingAlarmList(): NetworkState<BaseResponse<List<Push>>> =
        upComingAlarmListService.getUpComingAlarmList()
}
