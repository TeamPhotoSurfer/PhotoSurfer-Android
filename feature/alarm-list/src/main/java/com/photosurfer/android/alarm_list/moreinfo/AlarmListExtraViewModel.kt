package com.photosurfer.android.alarm_list.moreinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.AlarmElement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmListExtraViewModel @Inject constructor() : BaseViewModel() {

    private val _alarmData = MutableLiveData<List<AlarmElement>>()
    val alarmData: LiveData<List<AlarmElement>> = _alarmData
}
