package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.push_setting.PushSettingViewModel
import com.photosurfer.android.push_setting.R
import com.photosurfer.android.push_setting.databinding.FragmentPushMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PushMainFragment : BaseFragment<FragmentPushMainBinding>(R.layout.fragment_push_main) {

    private val pushSettingViewModel by activityViewModels<PushSettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pushSettingViewModel = pushSettingViewModel
        initDefaultAlarmDate()
        setDatePickerMinDate()
    }

    private fun initDefaultAlarmDate() {
        pushSettingViewModel.initDefaultAlarmDate()
    }

    private fun setDatePickerMinDate() {
        binding.pickerPush.minDate = System.currentTimeMillis() + ONE_DAY_TO_MILLIS
    }

    companion object {
        const val ONE_DAY_TO_MILLIS = 24 * 60 * 60 * 1000L
    }
}
