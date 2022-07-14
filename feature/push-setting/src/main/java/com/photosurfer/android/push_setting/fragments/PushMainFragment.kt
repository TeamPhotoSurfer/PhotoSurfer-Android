package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.DateUtil.dotDateFormatter
import com.photosurfer.android.push_setting.PushSettingViewModel
import com.photosurfer.android.push_setting.R
import com.photosurfer.android.push_setting.databinding.FragmentPushMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class PushMainFragment : BaseFragment<FragmentPushMainBinding>(R.layout.fragment_push_main) {

    private val pushSettingViewModel by activityViewModels<PushSettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pushSettingViewModel = pushSettingViewModel
        initDefaultAlarmDate()
        setDatePickerMinDate()
        getDateFromDatePicker()
    }

    private fun initDefaultAlarmDate() {
        pushSettingViewModel.initDefaultAlarmDate()
    }

    private fun setDatePickerMinDate() {
        binding.pickerPush.minDate = System.currentTimeMillis() + ONE_DAY_TO_MILLIS
    }

    private fun getDateFromDatePicker() {
        binding.pickerPush.setOnDateChangedListener { datePicker, year, month, day ->
            //월에 +1 하는것 데이트 피커가 1씩 뺀값을줌 이상한 부분 보정
            val tempDate = LocalDate.of(year, month+1, day).format(dotDateFormatter)
            pushSettingViewModel.updateAlarmDate(tempDate)
        }
    }

    companion object {
        const val ONE_DAY_TO_MILLIS = 24 * 60 * 60 * 1000L
    }
}
