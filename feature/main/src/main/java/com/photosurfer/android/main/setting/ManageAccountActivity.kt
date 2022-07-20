package com.photosurfer.android.main.setting

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.util.PhotoSurferSnackBar
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
        onClickLogout()
        onClickDeleteUser()
    }

    private fun onClickBackIcon() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun onClickLogout() {
        binding.clLogout.setOnClickListener {
            PhotoSurferSnackBar.make(binding.clLogout, PhotoSurferSnackBar.SETTING_ACTIVITY).show()
        }
    }

    private fun onClickDeleteUser() {
        binding.clDeleteUser.setOnClickListener {
            PhotoSurferSnackBar.make(binding.clDeleteUser, PhotoSurferSnackBar.SETTING_ACTIVITY)
                .show()
        }
    }
}