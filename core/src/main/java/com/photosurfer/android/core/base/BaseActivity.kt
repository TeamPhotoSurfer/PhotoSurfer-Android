package com.photosurfer.android.core.base

import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.photosurfer.android.core.util.EventObserver
import com.photosurfer.android.core.util.Injector
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    private val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.MainNavigatorInjector::class.java
        ).mainNavigator()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EntryPointAccessors.fromActivity(
            this,
            Injector.SharedPreferencesInjector::class.java
        ).sharedPreferences()
    }

    protected fun terminationTokenHandling(viewModel: BaseViewModel) {
        viewModel.moveToLogin.observe(this, EventObserver {
//            mainNavigator.openLogin(this) 이부분 추후에 작성해야함 로그인화면으로 이동부분
            sharedPreferences.edit { remove("PHOTO_SURFER_ACCESS_TOKEN") }
            sharedPreferences.edit { remove("USER_NICKNAME") }
            finishAffinity()
        })
    }
}

