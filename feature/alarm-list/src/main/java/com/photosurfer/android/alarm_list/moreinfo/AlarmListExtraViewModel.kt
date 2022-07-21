package com.photosurfer.android.alarm_list.moreinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.AlarmElement
import com.photosurfer.android.domain.repository.AlarmListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AlarmListExtraViewModel @Inject constructor(
    private val alarmListRepository: AlarmListRepository
) : BaseViewModel() {

    private val _alarmData = MutableLiveData<List<AlarmElement>>()
    val alarmData: LiveData<List<AlarmElement>> = _alarmData

    fun getPassedAlarmList() {
        viewModelScope.launch {
            alarmListRepository.getPassedAlarmList()
                .onSuccess { _alarmData.value = it }
                .onFailure { Timber.d(it, "${this.javaClass.name}_getPassedAlarmList") }
        }
    }

    fun getUpComingAlarmList() {
        viewModelScope.launch {
            alarmListRepository.getUpComingAlarmList()
                .onSuccess { _alarmData.value = it }
                .onFailure { Timber.d(it, "${this.javaClass.name}_getUpComingAlarmList") }
        }
    }
}
