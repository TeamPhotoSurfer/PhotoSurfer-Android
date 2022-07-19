package com.photosurfer.android.alarm_list.eachinfo

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.PUSH_ID
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.ZOOM_IN_IMAGE_URL
import com.photosurfer.android.alarm_list.R
import com.photosurfer.android.alarm_list.databinding.ActivityEachInfoBinding
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.util.getImageUriFromBitmap
import com.photosurfer.android.core.util.useBitmapImg
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class AlarmSpecificImageActivity : BaseActivity<ActivityEachInfoBinding>(R.layout.activity_each_info) {

    private val eachInfoViewModel by viewModels<EachInfoViewModel>()
    private lateinit var imgUrl: String
    private var pushId by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.eachInfoViewModel = eachInfoViewModel
        initExtraData()
        initViewModelData()
        initTransactionStartFragment()
        initBackButtonClickListener()
        initCloseButtonClickListener()
        initShareButtonClickListener()
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
        eachInfoViewModel.updateFragmentState(IMAGE_ZOOM_IN)
    }

    private fun initBackButtonClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initCloseButtonClickListener() {
        binding.btnClose.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace<ImageZoomInFragment>(R.id.container_each_info).commit()
            eachInfoViewModel.updateFragmentState(IMAGE_ZOOM_IN)
        }
    }

    private fun initShareButtonClickListener() {
        binding.btnShare.setOnClickListener {
            useBitmapImg(this, imgUrl) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_STREAM, getImageUriFromBitmap(this@AlarmSpecificImageActivity,it))
                }
                startActivity(Intent.createChooser(intent, "공유하기"))
            }
        }
    }

    override fun onBackPressed() {
        if (eachInfoViewModel.fragmentState.value == PUSH_MAIN_STATE) {
            supportFragmentManager.beginTransaction()
                .replace<ImageZoomInFragment>(R.id.container_each_info).commit()
            eachInfoViewModel.updateFragmentState(IMAGE_ZOOM_IN)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val IMAGE_ZOOM_IN = "IMAGE_ZOOM_IN"
        const val PUSH_MAIN_STATE = "PUSH_MAIN"
    }
}
