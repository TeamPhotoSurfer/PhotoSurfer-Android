package com.photosurfer.android.data.remote.service

import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.model.response.BaseResponse
import com.photosurfer.android.domain.entity.AlarmElement
import retrofit2.http.GET
import retrofit2.http.Path

interface SpecificAlarmService {
    @GET("push/{pushId}")
    suspend fun getSpecificAlarmInfo(
        @Path("pushId") pushId: Int
    ): NetworkState<BaseResponse<AlarmElement>>
}
