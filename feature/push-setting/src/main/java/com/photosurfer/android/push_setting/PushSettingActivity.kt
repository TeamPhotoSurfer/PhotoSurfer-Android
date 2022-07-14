package com.photosurfer.android.push_setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.core.base.BaseActivity
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
    }

    private fun initStartFragment() {
        transactionToPushMainFragment()
    }

    private fun initSelectTagCancelButtonClickListener() {
        binding.tvCancel.setOnClickListener {
            transactionToPushMainFragment()
        }
    }

    private fun initSelectTagSaveButtonCLickListener() {
        binding.tvSave.setOnClickListener {
            transactionToPushMainFragment()
            // 뷰모델 저장로직 호출부
        }
    }

    private fun transactionToPushMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace<PushMainFragment>(R.id.container_push_setting).commit()
    }
}
