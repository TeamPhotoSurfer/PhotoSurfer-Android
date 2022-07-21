package com.photosurfer.android.di

import com.photosurfer.android.data.remote.model.response.UrgentAlarmListResponse
import com.photosurfer.android.data.remote.service.ChooseTagService
import com.photosurfer.android.data.remote.service.PushSettingService
import com.photosurfer.android.data.remote.service.UrgentAlarmListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun providesPushSettingService(retrofit: Retrofit): PushSettingService =
        retrofit.create(PushSettingService::class.java)

    @Provides
    @Singleton
    fun providesUrgentAlarmListService(retrofit: Retrofit): UrgentAlarmListService =
        retrofit.create(UrgentAlarmListService::class.java)

    @Provides
    @Singleton
    fun providesTagListService(retrofit: Retrofit): ChooseTagService =
        retrofit.create(ChooseTagService::class.java)
}
