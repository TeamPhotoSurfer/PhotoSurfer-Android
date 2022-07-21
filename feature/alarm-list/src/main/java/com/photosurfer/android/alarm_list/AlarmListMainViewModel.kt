package com.photosurfer.android.alarm_list

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
class AlarmListMainViewModel @Inject constructor(
    private val urgentAlarmListRepository: AlarmListRepository
) : BaseViewModel() {

    private val _passedAlarmCount = MutableLiveData<Int>(0)
    val passedAlarmCount: LiveData<Int> = _passedAlarmCount

    private val _upComingAlarmCount = MutableLiveData<Int>(0)
    val upComingAlarmCount: LiveData<Int> = _upComingAlarmCount

    private val _urgentAlarmCount = MutableLiveData<Int>(0)
    val urgentAlarmCount: LiveData<Int> = _urgentAlarmCount

    private val _urgentAlarmList = MutableLiveData<List<AlarmElement>>(mutableListOf())
    val urgentAlarmList: LiveData<List<AlarmElement>> = _urgentAlarmList

    fun getUrgentAlarmList() {
        viewModelScope.launch {
            urgentAlarmListRepository.getUrgentAlarmList()
                .onSuccess {
                    _passedAlarmCount.value = it.passedCount
                    _upComingAlarmCount.value = it.upComingCount
                    _urgentAlarmCount.value = it.urgentCount
                    _urgentAlarmList.value = it.alarmList
                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_getUrgentAlarmList")
                }
        }
    }
}
