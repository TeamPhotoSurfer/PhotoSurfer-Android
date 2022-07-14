package com.photosurfer.android.navigator

import android.content.Context
import com.photosurfer.android.MainActivity
import com.photosurfer.android.core.ext.startActivity
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.startActivity<MainActivity>()
    }
}
