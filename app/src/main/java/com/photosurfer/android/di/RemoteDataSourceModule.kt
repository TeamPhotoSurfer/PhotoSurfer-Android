package com.photosurfer.android.di

import com.photosurfer.android.data.remote.datasource.*
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
    fun bindsRemoteAlarmListDataSource(source: RemoteAlarmListDataSourceImpl): RemoteAlarmListDataSource

    @Binds
    @Singleton
    fun bindsRemoteTagListDataSource(source: RemoteTagListDataSourceImpl): RemoteTagListDataSource

    @Binds
    @Singleton
    fun bindsRemoteSearchResultDataSource(source: RemoteSearchTagResultDataSourceImpl): RemoteSearchTagResultDataSource

    @Binds
    @Singleton
    fun bindsRemoteImageDataSource(source: RemoteImageDataSourceImpl): RemoteImageDataSource

    @Binds
    @Singleton
    fun bindsRemoteLoginDataSource(source: RemoteLoginDataSourceImpl): RemoteLoginDataSource
}
