package com.photosurfer.android.auth

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.photosurfer.android.auth.databinding.ActivityLoginBinding
import com.photosurfer.android.auth.socialloginmanager.KakaoLoginManager
import com.photosurfer.android.auth.socialloginmanager.NaverLoginManager
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.util.EventObserver
import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.shared.R.color
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var mainNavigator: MainNavigator

    @Inject
    lateinit var kakaoLoginManager: KakaoLoginManager

    @Inject
    lateinit var naverLoginManager: NaverLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.initAutoLoginState()
        setAnimationOnSplash()
        setStatusBarColorOnSplash()
        setLogoTransitionY()
        checkAutoLogin()
        viewModel.getFcmToken()
        onClickLoginBtn()
        initLoginObserver()
        initLoginStateObserver()
        initLoginFailureMessageObserver()
    }

    private fun checkAutoLogin() {
        val isAutoLogin = viewModel.isAutoLogin.value
        if (!requireNotNull(isAutoLogin)) setLoginViewGroupFadeIn()
        else navigateMainActivity()
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(this)
        finish()
    }

    private fun onClickLoginBtn() {
        with(binding) {
            clKakao.setOnClickListener {
                this@LoginActivity.viewModel.updatePlatform(KAKAO)
                kakaoLoginManager.startKakaoLogin(this@LoginActivity.viewModel.kakaoLoginCallback) {
                    this@LoginActivity.viewModel.updateSocialToken(it)
                }
            }

            clNaver.setOnClickListener {
                this@LoginActivity.viewModel.updatePlatform(NAVER)
                naverLoginManager.startNaverLogin {
                    this@LoginActivity.viewModel.updateSocialToken(it)
                }
            }
        }
    }

    private fun initLoginObserver() {
        viewModel.socialToken.observe(this) {
            viewModel.postLogin()
        }
    }

    private fun initLoginStateObserver() {
        viewModel.loginState.observe(
            this,
            EventObserver { navigateMainActivity() }
        )
    }

    private fun initLoginFailureMessageObserver() {
        viewModel.loginFailureMessage.observe(this) {
            Timber.d("로그인 실패")
            // 추후에 토스트 메시지 등등 원하는 처리
        }
    }

    private fun setAnimationOnSplash() {
        if (isOverAndroid12) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f).apply {
                    interpolator = AccelerateInterpolator()
                    duration = SPLASH_TIME
                    doOnEnd { splashScreenView.remove() }
                    start()
                }
            }
        }
    }

    private fun setStatusBarColorOnSplash() {
        this.window.statusBarColor =
            ContextCompat.getColor(this, color.login_blue_top)
    }

    private fun setLogoTransitionY() {
        ObjectAnimator.ofFloat(binding.ivLogo, "translationY", -420f).apply {
            interpolator = LinearInterpolator()
            setDelayIfDeviceOverAndroid12(this)
            duration = 1500L
            start()
        }
    }

    private fun setLoginViewGroupFadeIn() {
        ObjectAnimator.ofFloat(binding.clLogin, View.ALPHA, 0f, 1f).apply {
            interpolator = LinearInterpolator()
            setDelayIfDeviceOverAndroid12(this)
            duration = 1500L
            start()
        }
    }

    private fun setDelayIfDeviceOverAndroid12(objectAnimator: ObjectAnimator) {
        if (isOverAndroid12) {
            objectAnimator.startDelay = SPLASH_TIME + 300L
        }
    }

    private val isOverAndroid12 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    companion object {
        const val SPLASH_TIME = 300L
        private const val NAVER = "naver"
        private const val KAKAO = "kakao"
        private const val CLIENT_NAME = "PhotoSurfer"
    }
}
