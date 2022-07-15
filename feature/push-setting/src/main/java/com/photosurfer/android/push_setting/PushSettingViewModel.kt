package com.photosurfer.android.push_setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.constant.PushSettingConstant
import com.photosurfer.android.core.constant.PushSettingConstant.PUSH_MAIN
import com.photosurfer.android.core.util.DateUtil.dotDateFormatter
import com.photosurfer.android.domain.entity.TagInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PushSettingViewModel @Inject constructor() : BaseViewModel() {

    private val _alarmDate = MutableLiveData<String>()
    val alarmDate: LiveData<String> = _alarmDate

    private val _fragmentState = MutableLiveData<PushSettingConstant>(PUSH_MAIN)
    val fragmentState: LiveData<PushSettingConstant> = _fragmentState

    private val _wholeTagList = MutableLiveData<List<TagInfo>>()
    val wholeTagList: LiveData<List<TagInfo>> = _wholeTagList

    private val _representTagIdList = MutableLiveData<MutableList<Long>>()
    val representTagIdList: LiveData<MutableList<Long>> = _representTagIdList

    private val _tempRepresentTagIdList = MutableLiveData<MutableList<Long>>()
    val tempRepresentTagIdList: LiveData<MutableList<Long>> = _tempRepresentTagIdList

    fun initDefaultAlarmDate() {
        val defaultDate = LocalDate.now().plusDays(1)
        _alarmDate.postValue(defaultDate.format(dotDateFormatter))
    }

    fun updateAlarmDate(currentDate: String) {
        _alarmDate.postValue(currentDate)
    }

    fun updateFragmentState(fragmentState: PushSettingConstant) {
        _fragmentState.postValue(fragmentState)
    }

    fun updateWholeTagList(wholeTag: List<TagInfo>) {
        _wholeTagList.value = wholeTag
    }

    fun initRepresentTagIdList() {
        if ((_wholeTagList.value?.size ?: 1) < 3) {
            _representTagIdList.postValue(wholeTagList.value?.map { it.id }?.toMutableList())
        } else {
            _representTagIdList.postValue(
                wholeTagList.value?.subList(0, 3)?.map { it.id }?.toMutableList()
            )
        }
    }

    fun saveRepresentTagIdList() {
        _representTagIdList.value = tempRepresentTagIdList.value
    }

    fun updateTempRepresentTagList(tempList: MutableList<Long>){
        _tempRepresentTagIdList.postValue(tempList)
    }
}
