package com.photosurfer.android.di


import com.photosurfer.android.data.repository.*
import com.photosurfer.android.domain.repository.*
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

    @Binds
    @Singleton
    fun bindsImageRepository(repository: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    fun bindsSearchResultRepository(repository: PhotoRepositoryImpl): PhotoRepository

}
