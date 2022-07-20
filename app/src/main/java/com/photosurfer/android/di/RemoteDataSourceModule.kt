package com.photosurfer.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

//    예시
//    @Binds
//    @Singleton
//    fun bindsRemoteBookSearchDataSource(source: RemoteBookSearchDataSourceImpl): RemoteBookSearchDataSource
}
