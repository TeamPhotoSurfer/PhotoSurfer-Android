package com.photosurfer.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

//    예시
//    @Binds
//    @Singleton
//    fun bindsLocalPreferenceUserDataSource(source: LocalPreferenceUserDataSourceImpl): LocalPreferenceUserDataSource
}
