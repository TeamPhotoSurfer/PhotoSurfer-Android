package com.photosurfer.android.di

import com.photosurfer.android.data.remote.service.PushSettingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun providesPushSettingService(retrofit: Retrofit): PushSettingService =
        retrofit.create(PushSettingService::class.java)
}
