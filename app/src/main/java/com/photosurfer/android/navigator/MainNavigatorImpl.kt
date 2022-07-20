package com.photosurfer.android.navigator

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.photosurfer.android.MainActivity
import com.photosurfer.android.core.ext.startActivity
import com.photosurfer.android.push_setting.fragments.PushMainFragment
import com.photosurfer.android.search.SearchTagActivity
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.startActivity<MainActivity>()
    }

    override fun navigateSearchTag(context: Context, tag: Pair<String, String>) {
        context.startActivity<SearchTagActivity>(tag)
    }

    override fun transactionPushMainFragment(fragmentActivity: FragmentActivity, bundle: Bundle) {
        fragmentActivity.supportFragmentManager.beginTransaction().replace(
            com.photosurfer.android.alarm_list.R.id.container_each_info,
            PushMainFragment::class.java,
            bundle
        ).commit()
    }
}
