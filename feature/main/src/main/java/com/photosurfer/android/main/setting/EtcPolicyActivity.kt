package com.photosurfer.android.main.setting

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.main.R
import com.photosurfer.android.main.databinding.ActivityEctPolicyBinding

class EtcPolicyActivity : BaseActivity<ActivityEctPolicyBinding>(R.layout.activity_ect_policy) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackButtonClickListener()
        setPersonalDataClickListener()
        setOpenSourceClickListener()
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setPersonalDataClickListener() {
        binding.clPersonalData.setOnClickListener {
            PhotoSurferSnackBar.make(binding.clPersonalData, PhotoSurferSnackBar.SETTING_ACTIVITY)
                .show()
        }
    }

    private fun setOpenSourceClickListener() {
        binding.clOpenSource.setOnClickListener {
            PhotoSurferSnackBar.make(binding.clOpenSource, PhotoSurferSnackBar.SETTING_ACTIVITY)
                .show()
        }
    }
}