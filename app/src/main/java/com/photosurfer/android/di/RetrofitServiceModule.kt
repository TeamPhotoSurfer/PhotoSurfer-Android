package com.photosurfer.android.di

import com.photosurfer.android.data.remote.service.*
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

    @Provides
    @Singleton
    fun providesUrgentAlarmListService(retrofit: Retrofit): UrgentAlarmListService =
        retrofit.create(UrgentAlarmListService::class.java)

    @Provides
    @Singleton
    fun providesUpComingAlarmListService(retrofit: Retrofit): UpComingAlarmListService =
        retrofit.create(UpComingAlarmListService::class.java)

    @Provides
    @Singleton
    fun providesPassedAlarmListService(retrofit: Retrofit): PassedAlarmListService =
        retrofit.create(PassedAlarmListService::class.java)

    @Provides
    @Singleton
    fun providesSpecificAlarmService(retrofit: Retrofit): SpecificAlarmService =
        retrofit.create(SpecificAlarmService::class.java)

    @Provides
    @Singleton
    fun providesOftenSearchTagService(retrofit: Retrofit): OftenSearchTagService =
        retrofit.create(OftenSearchTagService::class.java)
}
