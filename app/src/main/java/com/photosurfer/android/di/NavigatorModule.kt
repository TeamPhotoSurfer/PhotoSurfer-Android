package com.photosurfer.android.di

import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.navigator.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface NavigatorModule {
    @Binds
    fun bindsMainNavigator(navigator: MainNavigatorImpl): MainNavigator
}
