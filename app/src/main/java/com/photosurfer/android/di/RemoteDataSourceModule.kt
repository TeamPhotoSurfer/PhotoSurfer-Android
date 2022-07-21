package com.photosurfer.android.di

import com.photosurfer.android.data.remote.datasource.RemoteChooseTagDataSource
import com.photosurfer.android.data.remote.datasource.RemoteChooseTagDataSourceImpl
import com.photosurfer.android.data.remote.datasource.RemotePushSettingDataSource
import com.photosurfer.android.data.remote.datasource.RemotePushSettingDataSourceImpl
import com.photosurfer.android.data.remote.datasource.RemoteUrgentAlarmListDataSource
import com.photosurfer.android.data.remote.datasource.RemoteUrgentAlarmListDataSourceImpl
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
    fun bindRemoteChooseTagDataSource(source: RemoteChooseTagDataSourceImpl): RemoteChooseTagDataSource

    @Binds
    @Singleton
    fun bindsRemoteUrgentAlarmListDataSource(source: RemoteUrgentAlarmListDataSourceImpl): RemoteUrgentAlarmListDataSource
}
