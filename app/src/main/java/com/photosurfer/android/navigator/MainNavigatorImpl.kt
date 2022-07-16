package com.photosurfer.android.navigator

import android.content.Context
import com.photosurfer.android.MainActivity
import com.photosurfer.android.core.ext.startActivity
import com.photosurfer.android.search.SearchTagActivity
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.startActivity<MainActivity>()
    }

    override fun navigateSearchTag(context: Context, tag: Pair<String, String>) {
        context.startActivity<SearchTagActivity>(tag)
    }

}
