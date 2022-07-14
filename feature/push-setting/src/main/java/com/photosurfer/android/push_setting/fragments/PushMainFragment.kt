package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.DateUtil.dotDateFormatter
import com.photosurfer.android.core.util.KeyBoardUtil
import com.photosurfer.android.core.util.KeyBoardVisibilityListener
import com.photosurfer.android.push_setting.PushSettingViewModel
import com.photosurfer.android.push_setting.R
import com.photosurfer.android.push_setting.databinding.FragmentPushMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class PushMainFragment : BaseFragment<FragmentPushMainBinding>(R.layout.fragment_push_main) {

    private val pushSettingViewModel by activityViewModels<PushSettingViewModel>()
    private lateinit var keyBoardVisibilityListener: KeyBoardVisibilityListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pushSettingViewModel = pushSettingViewModel
        initDefaultAlarmDate()
        setDatePickerMinDate()
        getDateFromDatePicker()
        initDatePickerButtonClickListener()
        initEditTextMemoFocusListener()
        initDatePickerButtonLongClickListener()
        initBackGroundClickListener()
        initKeyBoardVisibilityListener()
    }

    private fun initDefaultAlarmDate() {
        pushSettingViewModel.initDefaultAlarmDate()
    }

    private fun setDatePickerMinDate() {
        binding.pickerPush.minDate = System.currentTimeMillis() + ONE_DAY_TO_MILLIS
    }

    private fun getDateFromDatePicker() {
        binding.pickerPush.setOnDateChangedListener { datePicker, year, month, day ->
            // 월에 +1 하는것 데이트 피커가 1씩 뺀값을줌 이상한 부분 보정
            val tempDate = LocalDate.of(year, month + 1, day).format(dotDateFormatter)
            pushSettingViewModel.updateAlarmDate(tempDate)
        }
    }

    private fun initDatePickerButtonClickListener() {
        binding.layoutButtonDatePicker.setOnClickListener {
            keyBoardHideWhenEtMemoFocused {
                pushSettingViewModel.updateDatePickerVisibility(!requireNotNull(pushSettingViewModel.datePickerVisibility.value))
                pushSettingViewModel.updateDatePickerInfoVisibility(false)
            }
        }
    }

    private fun initDatePickerButtonLongClickListener() {
        binding.layoutButtonDatePicker.setOnLongClickListener {
            pushSettingViewModel.updateDatePickerInfoVisibility(true)
            return@setOnLongClickListener true
        }
    }

    private fun initBackGroundClickListener() {
        binding.layoutPushImage.setOnClickListener {
            pushSettingViewModel.updateDatePickerInfoVisibility(false)
            keyBoardHideWhenEtMemoFocused()
        }
        binding.pickerPush.setOnClickListener {
            pushSettingViewModel.updateDatePickerInfoVisibility(false)
            keyBoardHideWhenEtMemoFocused()
        }
        binding.layoutMemo.setOnClickListener {
            pushSettingViewModel.updateDatePickerInfoVisibility(false)
            keyBoardHideWhenEtMemoFocused()
        }
    }

    private fun initEditTextMemoFocusListener() {
        binding.etMemo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                pushSettingViewModel.updateDatePickerVisibility(false)
                pushSettingViewModel.updateDatePickerInfoVisibility(false)
                pushSettingViewModel.updatePushAlarmDoneButtonVisibility(false)
            } else {
                pushSettingViewModel.updatePushAlarmDoneButtonVisibility(true)
            }
        }
    }

    private fun initKeyBoardVisibilityListener() {
        keyBoardVisibilityListener = KeyBoardVisibilityListener(
            requireActivity().window,
            onHideKeyboard = { requireActivity().currentFocus?.clearFocus() },
            onShowKeyboard = { binding.scrollView.fullScroll(View.FOCUS_DOWN) }
        )
    }

    private fun keyBoardHideWhenEtMemoFocused(elseCase: (() -> Unit)? = null) {
        if (binding.etMemo.hasFocus()) {
            KeyBoardUtil.hide(requireActivity())
        } else {
            if (elseCase != null) {
                elseCase()
            }
        }
    }

    companion object {
        const val ONE_DAY_TO_MILLIS = 24 * 60 * 60 * 1000L
    }
}
