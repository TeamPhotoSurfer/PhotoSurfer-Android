package com.photosurfer.android.push_setting

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.PushSettingConstant
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
        pushSettingViewModel.updateWholeTagList(
            // 추후 받아온 태그 데이터로 교체
            listOf(
                TagInfo(1, "이창환"),
                TagInfo(2, "김효림"),
                TagInfo(3, "심채영"),
                TagInfo(4, "이호재"),
                TagInfo(5, "조재훈"),
                TagInfo(6, "이강민")
            )
        )
    }

    private fun initRepresentTagIdList() {
        pushSettingViewModel.initRepresentTagIdList()
    }
}
