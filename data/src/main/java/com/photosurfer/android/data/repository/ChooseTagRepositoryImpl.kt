package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteChooseTagDataSource
import com.photosurfer.android.data.remote.mapper.TagMapper
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.domain.entity.TagNameType
import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest
import com.photosurfer.android.domain.repository.ChooseTagRepository
import timber.log.Timber
import javax.inject.Inject

class ChooseTagRepositoryImpl @Inject constructor(
    private val remoteChooseTagDataSource: RemoteChooseTagDataSource,
    private val tagMapper: TagMapper
) : ChooseTagRepository {
    override suspend fun postTag(domainChooseTagRequest: DomainChooseTagRequest): Result<List<TagNameType>> {
        when(
            val response = remoteChooseTagDataSource.postTag(
                ChooseTagRequest(
                    file = domainChooseTagRequest.file,
                    tags = domainChooseTagRequest.tags.map{
                        tagMapper.toDomainTag(it)
                    }
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                response.body.data.tag.map{
                    tagMapper.toDataTag(it)
                }
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_postChooseTag"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_postChooseTag"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

}