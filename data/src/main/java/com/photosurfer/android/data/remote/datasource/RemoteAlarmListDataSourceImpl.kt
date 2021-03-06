package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.MoreAlarmListResponse
import com.photosurfer.android.data.remote.model.response.SpecificAlarmResponse
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import com.photosurfer.android.data.remote.service.PassedAlarmListService
import com.photosurfer.android.data.remote.service.SpecificAlarmService
import com.photosurfer.android.data.remote.service.UpComingAlarmListService
import com.photosurfer.android.data.remote.service.UrgentAlarmListService
import com.photosurfer.android.domain.entity.AlarmElement
import javax.inject.Inject

class RemoteAlarmListDataSourceImpl @Inject constructor(
    private val urgentAlarmListService: UrgentAlarmListService,
    private val passedAlarmListService: PassedAlarmListService,
    private val upComingAlarmListService: UpComingAlarmListService,
    private val specificAlarmService: SpecificAlarmService
) : RemoteAlarmListDataSource {
    override suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>> =
        urgentAlarmListService.getUrgentAlarmList()

    override suspend fun getPassedAlarmList(): NetworkState<BaseResponse<MoreAlarmListResponse>> =
        passedAlarmListService.getPassedAlarmList()

    override suspend fun getUpComingAlarmList(): NetworkState<BaseResponse<MoreAlarmListResponse>> =
        upComingAlarmListService.getUpComingAlarmList()

    override suspend fun getSpecificAlarmInfo(pushId: Int): NetworkState<BaseResponse<SpecificAlarmResponse>> =
        specificAlarmService.getSpecificAlarmInfo(pushId)
}
