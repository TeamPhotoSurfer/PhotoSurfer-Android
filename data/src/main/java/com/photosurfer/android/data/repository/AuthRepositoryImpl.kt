package com.photosurfer.android.data.repository

import com.photosurfer.android.core.exception.RetrofitFailureStateException
import com.photosurfer.android.core.firebasemessaging.FirebaseTokenManager
import com.photosurfer.android.data.local.datasource.LocalPreferenceUserDataSource
import com.photosurfer.android.data.remote.calladapter.NetworkState
import com.photosurfer.android.data.remote.datasource.RemoteLoginDataSource
import com.photosurfer.android.data.remote.model.request.LoginRequest
import com.photosurfer.android.domain.entity.LoginInfo
import com.photosurfer.android.domain.entity.request.DomainLoginRequest
import com.photosurfer.android.domain.repository.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseTokenManager: FirebaseTokenManager,
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource
) : AuthRepository {
    override fun getFcmToken(tokenCallBack: (String) -> Unit) {
        firebaseTokenManager.getFirebaseToken {
            tokenCallBack(it)
        }
    }

    override suspend fun postLogin(domainLoginRequest: DomainLoginRequest): Result<LoginInfo> {
        when (
            val response = remoteLoginDataSource.postLogin(
                LoginRequest(
                    socialToken = domainLoginRequest.socialToken,
                    socialType = domainLoginRequest.socialType,
                    fcm = domainLoginRequest.fcm
                )
            )
        ) {
            is NetworkState.Success -> return Result.success(
                LoginInfo(
                    successState = response.body.success,
                    accessToken = response.body.data.accesstoken
                )
            )
            is NetworkState.Failure -> return Result.failure(
                RetrofitFailureStateException(
                    response.error,
                    response.code
                )
            )
            is NetworkState.NetworkError -> Timber.d(
                response.error,
                "${this.javaClass.name}_postLogin"
            )
            is NetworkState.UnknownError -> Timber.d(
                response.t,
                "${this.javaClass.name}_postLogin"
            )
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }

    override fun saveAccessToken(accessToken: String) {
        localPreferenceUserDataSource.saveAccessToken(accessToken)
    }

    override fun getAccessToken(): String = localPreferenceUserDataSource.getAccessToken()
}
