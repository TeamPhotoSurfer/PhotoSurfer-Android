package com.photosurfer.android.di

import com.photosurfer.android.data.remote.datasource.RemotePushSettingDataSource
import com.photosurfer.android.data.remote.datasource.RemotePushSettingDataSourceImpl
import com.photosurfer.android.data.remote.datasource.RemoteAlarmListDataSource
import com.photosurfer.android.data.remote.datasource.RemoteAlarmListDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindsRemotePushSettingDataSource(source: RemotePushSettingDataSourceImpl): RemotePushSettingDataSource

    @Binds
    @Singleton
    fun bindsRemoteAlarmListDataSource(source: RemoteAlarmListDataSourceImpl): RemoteAlarmListDataSource
}
