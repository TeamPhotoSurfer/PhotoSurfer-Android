package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteUrgentAlarmListDataSource
import com.photosurfer.android.data.remote.mapper.PushMapper
import com.photosurfer.android.domain.entity.AlarmElement
import com.photosurfer.android.domain.entity.AlarmInfo
import com.photosurfer.android.domain.repository.UrgentAlarmListRepository
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

class UrgentAlarmListRepositoryImpl @Inject constructor(
    private val urgentAlarmListDataSource: RemoteUrgentAlarmListDataSource,
    private val pushMapper: PushMapper
) : UrgentAlarmListRepository {

    override suspend fun getUrgentAlarmList(): Result<AlarmInfo> {
        when (
            val response = urgentAlarmListDataSource.getUrgentAlarmList()
        ) {
            is NetworkState.Success -> {
                val todayDivider = AlarmElement(
                    id = -1,
                    pushDate = LocalDate.now(),
                    tags = listOf(),
                    imageURL = "",
                    memo = "",
                    photoId = -1
                )
                val tomorrowDivider = AlarmElement(
                    id = -2,
                    pushDate = LocalDate.now(),
                    tags = listOf(),
                    imageURL = "",
                    memo = "",
                    photoId = -1
                )
                val tempAlarmElementList = mutableListOf<AlarmElement>()
                tempAlarmElementList.add(todayDivider)
                tempAlarmElementList.addAll(
                    response.body.data.today.push.map {
                        pushMapper.toAlarmElement(
                            it
                        )
                    }
                )
                tempAlarmElementList.add(tomorrowDivider)
                tempAlarmElementList.addAll(
                    response.body.data.today.push.map {
                        pushMapper.toAlarmElement(
                            it
                        )
                    }
                )
                return Result.success(
                    AlarmInfo(
                        alarmList = tempAlarmElementList,
                        lastCount = response.body.data.lastCount,
                        upComingCount = response.body.data.comingCount,
                        urgentCount = response.body.data.todayTomorrowCount
                    )
                )
            }
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_getUrgentAlarmList"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_getUrgentAlarmList"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
