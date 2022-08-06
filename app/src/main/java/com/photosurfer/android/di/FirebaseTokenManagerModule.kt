package com.photosurfer.android.di

import com.photosurfer.android.core.firebasemessaging.FirebaseTokenManager
import com.photosurfer.android.firebasemessaging.FirebaseTokenManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseTokenManagerModule {

    @Binds
    fun bindsFirebaseTokenManager(fireBaseTokenManger: FirebaseTokenManagerImpl): FirebaseTokenManager
}
