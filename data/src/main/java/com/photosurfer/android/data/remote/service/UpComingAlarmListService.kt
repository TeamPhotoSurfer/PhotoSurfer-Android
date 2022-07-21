package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.data.remote.model.response.Push
import retrofit2.http.GET

interface UpComingAlarmListService {
    @GET("push/list/come")
    suspend fun getUpComingAlarmList(): NetworkState<BaseResponse<List<Push>>>
}
