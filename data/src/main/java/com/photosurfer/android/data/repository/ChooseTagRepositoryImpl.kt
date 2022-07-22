package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteChooseTagDataSource
import com.photosurfer.android.data.remote.datasource.RemoteTagListDataSource
import com.photosurfer.android.data.remote.model.request.ChooseTagRequest
import com.photosurfer.android.domain.entity.*
import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest
import com.photosurfer.android.domain.repository.ChooseTagRepository
import timber.log.Timber
import javax.inject.Inject

class ChooseTagRepositoryImpl @Inject constructor(
    private val remoteChooseTagDataSource: RemoteChooseTagDataSource,
    private val remoteTagListDataSource: RemoteTagListDataSource
) : ChooseTagRepository {
    override suspend fun postTag(domainChooseTagRequest: DomainChooseTagRequest): Result<List<TagIdNameType>> {
        when(
            val response = remoteChooseTagDataSource.postTag(
                ChooseTagRequest(
                    file = domainChooseTagRequest.file,
                    tags = domainChooseTagRequest.tags
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                response.body.data.tag
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

    override suspend fun getTagList(): Result<ThreeTagList> {
        when(
            val response = remoteTagListDataSource.getTagList()
        ) {
            is NetworkState.Success -> {
                val tempOftenTagList = mutableListOf<TagInfo>()
                val tempRecentTagList = mutableListOf<TagInfo>()
                val tempPlatformTagList = mutableListOf<TagInfo>()

                tempOftenTagList.addAll(
                    response.body.data.often.tags
                )
                tempRecentTagList.addAll(
                    response.body.data.recent.tags
                )
                tempPlatformTagList.addAll(
                    response.body.data.platform.tags
                )
                return Result.success(
                    ThreeTagList(
                        recentTagList = tempRecentTagList,
                        oftenTagList = tempOftenTagList,
                        platformTagList = tempPlatformTagList
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
        return Result.failure(java.lang.IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun getSavedTagList(): Result<SavedTagList> {
        when(
            val response = remoteTagListDataSource.getSavedTagList()
        ) {
            is NetworkState.Success -> {
                val tempBookmarkedList = mutableListOf<SavedTag>()
                val tempNotBookmarkedList = mutableListOf<SavedTag>()

                tempBookmarkedList.addAll(
                    response.body.data.bookmarked.tags
                )
                tempNotBookmarkedList.addAll(
                    response.body.data.notBookmarked.tags
                )
                return Result.success(
                    SavedTagList(
                        bookmarked = tempBookmarkedList,
                        notBookmarked = tempNotBookmarkedList
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
        return Result.failure(java.lang.IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override suspend fun putNewTagName(): Result<String> {
        when(
            val response = remoteTagListDataSource.putEditTagName()
        ) {
            is NetworkState.Success -> {

                return Result.success(
                    response.body.data.name
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
                "${this.javaClass.name}_putNewTagName"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_putNewTagName"
            )
        }
        return Result.failure(java.lang.IllegalStateException("NetworkError or UnKnownError please check timber"))

    }

    override suspend fun getAllTagList(): Result<MutableList<TagInfo>> {
        when(
            val response = remoteTagListDataSource.getAllTagList()
        ) {
            is NetworkState.Success -> {
                return Result.success(
                    response.body.data
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
                "${this.javaClass.name}_getAllTagList"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_getAllTagList"
            )
        }
        return Result.failure(java.lang.IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

}