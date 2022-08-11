package com.photosurfer.android.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.util.Event
import com.photosurfer.android.domain.entity.request.DomainLoginRequest
import com.photosurfer.android.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _loginState = MutableLiveData<Event<Boolean>>()
    val loginState: LiveData<Event<Boolean>> = _loginState

    val isAutoLogin = MutableLiveData(false)

    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Timber.d("${error.message} OAuthLoginCallback 부분에서의 오류 onError")
        } else if (token != null) {
            updateSocialToken(token.accessToken)
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

    fun postLogin() {
        viewModelScope.launch {
            authRepository.postLogin(
                DomainLoginRequest(
                    socialToken = socialToken.value ?: "",
                    socialType = platform,
                    fcm = fcmToken.value ?: ""
                )
            ).onSuccess {
                if (it.successState) {
                    saveAccessToken(it.accessToken)
                    _loginState.value = Event(true)
                }
            }.onFailure {
                _loginFailureMessage.value = it.message
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

    fun saveAccessToken(accessToken: String) {
        authRepository.saveAccessToken(accessToken)
    }

    fun initAutoLoginState() {
        if (authRepository.getAccessToken() != "") {
            isAutoLogin.value = true
        }
    }
}
