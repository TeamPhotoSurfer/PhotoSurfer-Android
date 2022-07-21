package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteTagListDataSource
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.repository.TagListRepository
import timber.log.Timber
import javax.inject.Inject

class TagListRepositoryImpl @Inject constructor(
    private val remoteTagListDataSource: RemoteTagListDataSource
) : TagListRepository {

    override suspend fun getOftenSearchTagList(): Result<List<TagInfo>> {
        val response = remoteTagListDataSource.getOftenSearchTagList()
        when (response) {
            is NetworkState.Success -> return Result.success(response.body.data.tags)

            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(response.error, response.code)
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error, "${this.javaClass.name}_getUrgentAlarmList"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t, "${this.javaClass.name}_getUrgentAlarmList"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}