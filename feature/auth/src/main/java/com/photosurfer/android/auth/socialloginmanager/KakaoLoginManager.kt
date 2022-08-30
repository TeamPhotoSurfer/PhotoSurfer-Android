package com.photosurfer.android.auth.socialloginmanager

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ActivityContext
import timber.log.Timber
import javax.inject.Inject

class KakaoLoginManager @Inject constructor(
    @ActivityContext private val context: Context
) {
    fun startKakaoLogin(
        updateSocialToken: (String) -> Unit
    ) {
        val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Timber.d("${error.message} OAuthLoginCallback 부분에서의 오류 onError")
            } else if (token != null) {
                updateSocialToken(token.accessToken)
            }
        }

        val kakaoLoginState = if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            KaKaoLoginState.KAKAO_TALK_LOGIN
        } else {
            KaKaoLoginState.KAKAO_ACCOUNT_LOGIN
        }

        when (kakaoLoginState) {
            KaKaoLoginState.KAKAO_TALK_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Timber.d("${error.message} OAuthLoginCallback 부분에서의 오류 onError")

                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        UserApiClient.instance.loginWithKakaoAccount(
                            context,
                            callback = kakaoLoginCallback
                        )
                    } else if (token != null) {
                        updateSocialToken(token.accessToken)
                    }
                }
            }
            KaKaoLoginState.KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = kakaoLoginCallback
                )
            }
        }
    }
}

enum class KaKaoLoginState {
    KAKAO_TALK_LOGIN, KAKAO_ACCOUNT_LOGIN
}
