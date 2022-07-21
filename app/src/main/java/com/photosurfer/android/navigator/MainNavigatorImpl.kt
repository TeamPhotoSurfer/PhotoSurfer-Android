package com.photosurfer.android.navigator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.photosurfer.android.MainActivity
import com.photosurfer.android.core.ext.startActivity
import com.photosurfer.android.domain.entity.SerializeTagInfoList
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.push_setting.fragments.PushMainFragment
import com.photosurfer.android.search.SearchTagActivity
import com.photosurfer.android.search_result.SearchResultActivity
import javax.inject.Inject

class MainNavigatorImpl @Inject constructor() : MainNavigator {
    override fun navigateMain(context: Context) {
        context.startActivity<MainActivity>()
    }

    override fun navigateSearchTag(context: Context, selectedTag: TagInfo?) {
        context.startActivity(
            Intent(context, SearchTagActivity::class.java).apply {
                putExtra(SELECTED_TAG, selectedTag)
            }
        )
    }

    override fun transactionPushMainFragment(fragmentActivity: FragmentActivity, bundle: Bundle) {
        fragmentActivity.supportFragmentManager.beginTransaction().replace(
            com.photosurfer.android.alarm_list.R.id.container_each_info,
            PushMainFragment::class.java,
            bundle
        ).commit()
    }

    override fun navigateSearchResult(context: Context, list: List<TagInfo>) {
        context.startActivity(
            Intent(context, SearchResultActivity::class.java).apply {
                putExtra(TAG_LIST, SerializeTagInfoList(list))
            }
        )
    }

    companion object {
        const val TAG_LIST = "TAG_LIST"
        const val SELECTED_TAG = "SELECTED_TAG"
    }
}
