package com.photosurfer.android.di

import com.photosurfer.android.data.repository.ChooseTagRepositoryImpl
import com.photosurfer.android.data.repository.PushSettingRepositoryImpl
import com.photosurfer.android.domain.repository.ChooseTagRepository
import com.photosurfer.android.data.repository.UrgentAlarmListRepositoryImpl
import com.photosurfer.android.domain.repository.PushSettingRepository
import com.photosurfer.android.domain.repository.UrgentAlarmListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsPushSettingRepository(repository: PushSettingRepositoryImpl): PushSettingRepository

    @Binds
    @Singleton
    fun bindsChooseTagRepository(repository: ChooseTagRepositoryImpl): ChooseTagRepository


    @Binds
    @Singleton
    fun bindsUrgentAlarmListRepository(repository: UrgentAlarmListRepositoryImpl): UrgentAlarmListRepository
}
