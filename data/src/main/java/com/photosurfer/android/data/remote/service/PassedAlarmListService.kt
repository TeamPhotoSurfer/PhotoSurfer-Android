package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.MoreAlarmListResponse
import com.photosurfer.android.data.remote.model.response.Push
import retrofit2.http.GET

interface PassedAlarmListService {
    @GET("push/list/last")
    suspend fun getPassedAlarmList(): NetworkState<BaseResponse<MoreAlarmListResponse>>
}
