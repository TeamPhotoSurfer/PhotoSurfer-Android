package com.photosurfer.android.di

import com.photosurfer.android.data.repository.PushSettingRepositoryImpl
import com.photosurfer.android.domain.repository.PushSettingRepository
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
}
