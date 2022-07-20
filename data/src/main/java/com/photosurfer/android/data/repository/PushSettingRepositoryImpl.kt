package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemotePushSettingDataSource
import com.photosurfer.android.data.remote.model.request.PushSettingRequest
import com.photosurfer.android.domain.entity.request.DomainPushSettingRequest
import com.photosurfer.android.domain.repository.PushSettingRepository
import timber.log.Timber
import javax.inject.Inject

class PushSettingRepositoryImpl @Inject constructor(
    private val remotePushSettingDataSource: RemotePushSettingDataSource
) : PushSettingRepository {

    override suspend fun postPushSetting(
        photoId: Int,
        domainPushSettingRequest: DomainPushSettingRequest
    ): Result<String> {
        when (
            val response = remotePushSettingDataSource.postPushSetting(
                photoId,
                PushSettingRequest(
                    pushDate = domainPushSettingRequest.pushDate,
                    tagIds = domainPushSettingRequest.tagIds,
                    memo = domainPushSettingRequest.memo
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                response.body.message
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_postPushSetting"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_postPushSetting"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
