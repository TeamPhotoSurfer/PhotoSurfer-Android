package com.photosurfer.android.core.util

import android.content.SharedPreferences
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

interface Injector {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface MainNavigatorInjector {
        fun mainNavigator(): MainNavigator
    }

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface SharedPreferencesInjector {
        fun sharedPreferences(): SharedPreferences
    }
}
