package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteImageDataSource
import com.photosurfer.android.data.remote.mapper.ImageMapper
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.domain.entity.DetailImageInfo
import com.photosurfer.android.domain.repository.ImageRepository
import timber.log.Timber
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteImageDataSource: RemoteImageDataSource,
    private val imageMapper: ImageMapper
) : ImageRepository {

    override suspend fun getDetailImageInfo(photoId: Int): Result<DetailImageInfo> {
        when (
            val response = remoteImageDataSource.getDetailImageInfo(photoId)
        ) {
            is NetworkState.Success -> return Result.success(
                imageMapper.toDetailImageInfo(response.body.data)
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_getDetailImageInfo"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_getDetailImageInfo"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}
