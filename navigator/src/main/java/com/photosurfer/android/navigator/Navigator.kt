package com.photosurfer.android.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.photosurfer.android.domain.entity.TagInfo

interface MainNavigator {

    fun navigateMain(context: Context)

    fun navigateSearchTag(
        context: Context,
        tag: Pair<String, String>
    )

    fun transactionPushMainFragment(fragmentActivity: FragmentActivity, bundle: Bundle)

    fun navigateSearchResult(
        context: Context, list: List<TagInfo>
    )
}
