package com.photosurfer.android.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakao.sdk.auth.model.OAuthToken
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private val _fcmToken = MutableLiveData<String>()
    val fcmToken: LiveData<String> = _fcmToken

    private lateinit var platform: String

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage

    val isAutoLogin = false // TODO : sharedPreference에서 불러오는 데이터로 변경할 것

    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Timber.d("${error.message} OAuthLoginCallback 부분에서의 오류 onError")
        } else if (token != null) {
            _socialToken.value = token.accessToken
        }
    }

    fun naverSetOAuthLoginCallback() {
        oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                _socialToken.value = NaverIdLoginSDK.getAccessToken()
            }

            override fun onError(errorCode: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onError")
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onFailure")
            }
        }
    }

    fun updateSocialToken(socialToken: String) {
        _socialToken.value = socialToken
    }

    fun updatePlatform(platform: String) {
        this.platform = platform
    }

    fun getFcmToken() {
        authRepository.getFcmToken {
            updateFcmToken(it)
        }
    }

    fun updateFcmToken(fcmToken: String) {
        _fcmToken.value = fcmToken
    }
}
