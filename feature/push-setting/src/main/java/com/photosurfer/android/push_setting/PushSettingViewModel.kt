package com.photosurfer.android.push_setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.constant.PushSettingConstant
import com.photosurfer.android.core.constant.PushSettingConstant.PUSH_MAIN
import com.photosurfer.android.core.util.DateUtil.dotDateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PushSettingViewModel @Inject constructor() : BaseViewModel() {

    private val _alarmDate = MutableLiveData<String>()
    val alarmDate: LiveData<String> = _alarmDate

    private val _fragmentState = MutableLiveData<PushSettingConstant>(PUSH_MAIN)
    val fragmentState: LiveData<PushSettingConstant> = _fragmentState

    fun initDefaultAlarmDate() {
        val defaultDate = LocalDate.now().plusDays(1)
        _alarmDate.postValue(defaultDate.format(dotDateFormatter))
    }

    fun updateAlarmDate(currentDate: String) {
        _alarmDate.postValue(currentDate)
    }
}
