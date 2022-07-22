package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteSearchTagResultDataSource
import com.photosurfer.android.domain.entity.request.SearchTagResult
import com.photosurfer.android.domain.repository.PhotoRepository
import timber.log.Timber
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val remoteSearchTagResultDataSource: RemoteSearchTagResultDataSource
) : PhotoRepository {

    override suspend fun getPhotoListByTag(options: Map<String, Int>): Result<SearchTagResult> {
        val response = remoteSearchTagResultDataSource.getSearchTagResultList(options)
        when (response) {
            is NetworkState.Success -> {
                val tags = response.body.data.tags
                val photos = response.body.data.photos
                val rtn = SearchTagResult(tags, photos)
                return Result.success(rtn)
            }

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