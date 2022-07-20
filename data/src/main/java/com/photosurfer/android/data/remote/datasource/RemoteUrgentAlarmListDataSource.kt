package com.photosurfer.android.data.remote.datasource

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse

interface RemoteUrgentAlarmListDataSource {

    suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>>
}
