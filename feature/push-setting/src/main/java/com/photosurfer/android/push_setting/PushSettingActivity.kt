package com.photosurfer.android.push_setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.PushSettingConstant.PUSH_MAIN
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.push_setting.databinding.ActivityPushSettingBinding
import com.photosurfer.android.push_setting.fragments.PushMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PushSettingActivity :
    BaseActivity<ActivityPushSettingBinding>(R.layout.activity_push_setting) {

    private val pushSettingViewModel by viewModels<PushSettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.pushSettingViewModel = pushSettingViewModel
        initStartFragment()
        initSelectTagSaveButtonCLickListener()
        initSelectTagCancelButtonClickListener()
        initExtraData()
        initRepresentTagIdList()
    }

    private fun initStartFragment() {
        transactionToPushMainFragment()
    }

    private fun initSelectTagCancelButtonClickListener() {
        binding.tvCancel.setOnClickListener {
            transactionToPushMainFragment()
            pushSettingViewModel.updateFragmentState(PUSH_MAIN)
        }
    }

    private fun initSelectTagSaveButtonCLickListener() {
        binding.tvSave.setOnClickListener {
            transactionToPushMainFragment()
            pushSettingViewModel.updateFragmentState(PUSH_MAIN)
            pushSettingViewModel.saveRepresentTagIdList()
        }
    }

    private fun transactionToPushMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace<PushMainFragment>(R.id.container_push_setting).commit()
    }

    private fun initExtraData() {
        val photoId = intent.getIntExtra("PHOTO_ID", -1)
        val tagInfoList = intent.getSerializableExtra("TAG_INFO_LIST")
        pushSettingViewModel.updateWholeTagList(
            // 추후 받아온 태그 데이터로 교체
            listOf(
                TagInfo(41, "침대2345"),
                TagInfo(44, "뉴우우에어팟"),
                TagInfo(37, "침대"),
                TagInfo(43, "침대234523"),
                TagInfo(65, "침대2수정")
            )
        )
        pushSettingViewModel.updatePhotoId(88)
    }

    private fun initRepresentTagIdList() {
        pushSettingViewModel.initRepresentTagIdList()
    }
}
