package com.photosurfer.android.main.setting

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
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
            // 개인정보 처리방침
        }
    }

    private fun setOpenSourceClickListener() {
        binding.clOpenSource.setOnClickListener {
            // 오픈소스 라이선스
        }
    }
}