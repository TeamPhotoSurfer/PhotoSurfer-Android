package com.photosurfer.android.di


import com.photosurfer.android.data.repository.AlarmListRepositoryImpl
import com.photosurfer.android.data.repository.ChooseTagRepositoryImpl
import com.photosurfer.android.data.repository.PushSettingRepositoryImpl
import com.photosurfer.android.data.repository.TagListRepositoryImpl
import com.photosurfer.android.domain.repository.AlarmListRepository
import com.photosurfer.android.domain.repository.ChooseTagRepository
import com.photosurfer.android.domain.repository.PushSettingRepository
import com.photosurfer.android.domain.repository.TagListRepository
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
    fun bindsAlarmListRepository(repository: AlarmListRepositoryImpl): AlarmListRepository

    @Binds
    @Singleton
    fun bindsTagListRepository(repository: TagListRepositoryImpl): TagListRepository

}
