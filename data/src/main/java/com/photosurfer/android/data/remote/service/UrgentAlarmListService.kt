package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import retrofit2.http.GET

interface UrgentAlarmListService {
    @GET("push/list/today")
    suspend fun getUrgentAlarmList(): NetworkState<BaseResponse<UrgentAlarmListResponse>>
}
