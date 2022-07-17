package com.photosurfer.android.main.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.main.R
import com.photosurfer.android.main.databinding.FragmentSettingBinding
import com.photosurfer.android.main.setting.viewModel.SettingViewModel
import timber.log.Timber

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        iniClickEvent()
    }

    private fun iniClickEvent() {
        with(binding) {
            clManageAccount.setOnClickListener {
                startActivity(Intent(activity, ManageAccountActivity::class.java))
            }
            clEtcPolicy.setOnClickListener {
                Timber.d("setting / 기타정책")
            }
        }
    }
}