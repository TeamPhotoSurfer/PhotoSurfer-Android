package com.photosurfer.android.di

import com.photosurfer.android.data.local.datasource.LocalPreferenceUserDataSource
import com.photosurfer.android.data.local.datasource.LocalPreferenceUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindsLocalPreferenceUserDataSource(source: LocalPreferenceUserDataSourceImpl): LocalPreferenceUserDataSource
}
