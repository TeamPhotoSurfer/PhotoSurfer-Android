package com.photosurfer.android.navigator

import android.content.Context

interface MainNavigator {

    fun navigateMain(context: Context)

    fun navigateSearchTag(
        context: Context,
        tag: Pair<String, String>
    )

}
