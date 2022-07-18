package com.photosurfer.android.alarm_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.AlarmElement
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlarmListExtraViewModel @Inject constructor() : BaseViewModel() {

    private val _fragmentState = MutableLiveData<Int>()
    val fragmentState: LiveData<Int> = _fragmentState

    private val _alarmData = MutableLiveData<List<AlarmElement>>()
    val alarmData: LiveData<List<AlarmElement>> = _alarmData

    private val _zoomInImageUrl = MutableLiveData<String>()
    val zoomInImageUrl: LiveData<String> = _zoomInImageUrl

    fun updateZoomInImageUrl(zoomInUrl: String) {
        _zoomInImageUrl.postValue(zoomInUrl)
    }

    fun updateFragmentState(state: Int) {
        _fragmentState.postValue(state)
    }
}
