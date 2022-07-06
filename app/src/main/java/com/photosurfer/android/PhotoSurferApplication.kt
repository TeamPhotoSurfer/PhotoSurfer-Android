package com.photosurfer.android

import android.app.Application
import com.photosurfer.android.core.util.PhotoSurferDebugTree
import timber.log.Timber

class PhotoSurferApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(PhotoSurferDebugTree())
        }
    }
}
