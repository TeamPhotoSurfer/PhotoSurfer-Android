package com.photosurfer.android.alarm_list.eachinfo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.PUSH_ID
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.ZOOM_IN_IMAGE_URL
import com.photosurfer.android.alarm_list.R
import com.photosurfer.android.alarm_list.databinding.ActivityEachInfoBinding
import com.photosurfer.android.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class EachInfoActivity : BaseActivity<ActivityEachInfoBinding>(R.layout.activity_each_info) {

    private val eachInfoViewModel by viewModels<EachInfoViewModel>()
    private lateinit var imgUrl: String
    private var pushId by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initExtraData()
        initViewModelData()
        initTransactionStartFragment()
    }

    private fun initExtraData() {
        imgUrl = intent.getStringExtra(ZOOM_IN_IMAGE_URL) ?: "오류발생"
        pushId = intent.getLongExtra(PUSH_ID, -3L)
    }

    private fun initViewModelData() {
        eachInfoViewModel.updateImgUrl(imgUrl)
        eachInfoViewModel.updatePushId(pushId)
    }

    private fun initTransactionStartFragment() {
        supportFragmentManager.beginTransaction()
            .replace<ImageZoomInFragment>(R.id.container_each_info).commit()
    }
}
