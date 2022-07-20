package com.photosurfer.android.main.setting

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.main.R
import com.photosurfer.android.main.databinding.ActivityManageAccountBinding
import com.photosurfer.android.main.setting.viewModel.SettingViewModel

class ManageAccountActivity :
    BaseActivity<ActivityManageAccountBinding>(R.layout.activity_manage_account) {
    private val viewModel: SettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        onClickBackIcon()
    }

    private fun onClickBackIcon() {
        binding.ivBack.setOnClickListener { finish() }
    }
}