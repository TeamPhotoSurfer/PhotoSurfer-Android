package com.photosurfer.android.auth

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.photosurfer.android.auth.databinding.ActivityLoginBinding
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.shared.R.color
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setAnimationOnSplash()
        setStatusBarColorOnSplash()
        setLogoTransitionY()
    }

    private fun setAnimationOnSplash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val fadeOut = ObjectAnimator.ofFloat(splashScreenView, View.ALPHA, 1f, 0f)
                fadeOut.interpolator = AccelerateInterpolator()
                fadeOut.duration = SPLASH_TIME
                fadeOut.doOnEnd {
                    splashScreenView.remove()
                }
                fadeOut.start()
            }
        }
    }

    private fun setStatusBarColorOnSplash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.window.statusBarColor =
                ContextCompat.getColor(this, color.login_blue_top)
        }
    }

    private fun setLogoTransitionY() {
        val transitionY = ObjectAnimator.ofFloat(binding.ivLogo, "translationY", -420f).apply {
            interpolator = AccelerateInterpolator()
            setDelayIfDeviceOverAndroid12(this)
            duration = 1500L
        }
        transitionY.start()
    }

    private fun setDelayIfDeviceOverAndroid12(objectAnimator: ObjectAnimator) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) objectAnimator.startDelay =
            SPLASH_TIME + 300L
    }

    companion object {
        const val SPLASH_TIME = 300L
    }
}