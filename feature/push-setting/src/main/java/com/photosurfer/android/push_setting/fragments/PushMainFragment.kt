package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.constant.PushSettingConstant.SELECT_TAG
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
    private var clickableState = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pushSettingViewModel = pushSettingViewModel
        initDefaultAlarmDate()
        initVisibilityVariable()
        initArgumentsData()
        binding.clickableState = clickableState
        setDatePickerMinDate()
        getDateFromDatePicker()
        initDatePickerButtonClickListener()
        initEditTextMemoFocusListener()
        initDatePickerButtonLongClickListener()
        initBackGroundClickListener()
        initKeyBoardVisibilityListener()
        initRepresentTagButtonClickListener()
    }

    private fun initArgumentsData() {
        if (arguments != null) {
            clickableState = requireNotNull(arguments).getBoolean("CLICKABLE_STATE")
        }
    }

    private fun initDefaultAlarmDate() {
        pushSettingViewModel.initDefaultAlarmDate()
    }

    private fun initVisibilityVariable() {
        binding.datePickerVisibility = false
        binding.datePickerInfoVisibility = false
        binding.pushAlarmDoneButtonVisibility = true
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
            hideKeyBoardWhenMemoETFocused {
                binding.datePickerVisibility =
                    !requireNotNull(binding.datePickerVisibility)
                binding.datePickerInfoVisibility = false
            }
        }
    }

    private fun initDatePickerButtonLongClickListener() {
        binding.layoutButtonDatePicker.setOnLongClickListener {
            binding.datePickerInfoVisibility = true
            return@setOnLongClickListener true
        }
    }

    private fun initBackGroundClickListener() {
        with(binding) {
            layoutPushImage.setOnClickListener {
                binding.datePickerInfoVisibility = false
                hideKeyBoardWhenMemoETFocused()
            }
            pickerPush.setOnClickListener {
                binding.datePickerInfoVisibility = false
                hideKeyBoardWhenMemoETFocused()
            }
            layoutMemo.setOnClickListener {
                binding.datePickerInfoVisibility = false
                hideKeyBoardWhenMemoETFocused()
            }
        }
    }

    private fun initEditTextMemoFocusListener() {
        binding.etMemo.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.datePickerVisibility = false
                binding.datePickerInfoVisibility = false
                binding.pushAlarmDoneButtonVisibility = false
            } else {
                binding.pushAlarmDoneButtonVisibility = true
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

    private fun hideKeyBoardWhenMemoETFocused(elseCase: (() -> Unit)? = null) {
        if (binding.etMemo.hasFocus()) {
            KeyBoardUtil.hide(requireActivity())
        } else {
            if (elseCase != null) {
                elseCase()
            }
        }
    }

    private fun initRepresentTagButtonClickListener() {
        binding.layoutButtonRepresentTag.setOnClickListener {
            transactionToSelectTagFragment()
            pushSettingViewModel.updateFragmentState(SELECT_TAG)
        }
    }

    private fun transactionToSelectTagFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace<SelectTagFragment>(R.id.container_push_setting).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        keyBoardVisibilityListener.detachKeyboardListeners()
    }

    companion object {
        const val ONE_DAY_TO_MILLIS = 24 * 60 * 60 * 1000L
    }
}
