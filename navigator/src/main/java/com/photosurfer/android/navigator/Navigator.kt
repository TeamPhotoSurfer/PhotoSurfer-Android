package com.photosurfer.android.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.photosurfer.android.domain.entity.TagInfo

interface MainNavigator {

    fun navigateMain(context: Context)

    fun navigateSearchTag(
        context: Context,
        selectedTag: TagInfo? = null
    )

    fun transactionPushMainFragment(fragmentActivity: FragmentActivity, bundle: Bundle)

    fun navigateSearchResult(
        context: Context, list: List<TagInfo>
    )

    fun navigatePushSettingFragment(context: Context, list: List<TagInfo>)
}
