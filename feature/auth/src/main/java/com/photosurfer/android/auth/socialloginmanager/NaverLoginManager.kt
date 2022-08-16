package com.photosurfer.android.auth.socialloginmanager

import android.content.Context
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.photosurfer.android.auth.BuildConfig.X_NAVER_CLIENT_ID
import com.photosurfer.android.auth.BuildConfig.X_NAVER_CLIENT_SECRET
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class NaverLoginManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    lateinit var oAuthLoginCallback: OAuthLoginCallback
        private set

    fun naverSetOAuthLoginCallback(updateSocialToken: (String) -> Unit) {
        oAuthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                updateSocialToken(NaverIdLoginSDK.getAccessToken() ?: "")
            }

            override fun onError(errorCode: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onError")
            }

            override fun onFailure(httpStatus: Int, message: String) {
                Timber.d("$message OAuthLoginCallback 부분에서의 오류 onFailure")
            }
        }
    }

    fun startNaverLogin(updateSocialToken: (String) -> Unit) {
        naverSetOAuthLoginCallback { updateSocialToken(it) }
        NaverIdLoginSDK.initialize(
            context,
            X_NAVER_CLIENT_ID,
            X_NAVER_CLIENT_SECRET,
            CLIENT_NAME
        )
        NaverIdLoginSDK.authenticate(context, oAuthLoginCallback)
    }

    companion object {
        private const val CLIENT_NAME = "PhotoSurfer"
    }
}
