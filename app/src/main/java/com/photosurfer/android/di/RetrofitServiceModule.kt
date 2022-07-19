package com.photosurfer.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

//    예시
//    @Provides
//    @Singleton
//    fun providesNaverBookSearchService(@NaverBookSearchServer retrofit: Retrofit): NaverBookSearchService =
//        retrofit.create(NaverBookSearchService::class.java)
}
