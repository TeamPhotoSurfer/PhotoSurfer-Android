package com.photosurfer.android.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private lateinit var platform: String

    private val _loginFailureMessage = MutableLiveData<String>()
    val loginFailureMessage: LiveData<String> = _loginFailureMessage

    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    val isAutoLogin = false // TODO : sharedPreference에서 불러오는 데이터로 변경할 것

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

    fun updatePlatform(platform: String) {
        this.platform = platform
    }
}
