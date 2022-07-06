package com.photosurfer.android.core.util

import timber.log.Timber

class PhotoSurferDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}
