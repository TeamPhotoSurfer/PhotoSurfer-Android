package com.photosurfer.android.push_setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.constant.PushSettingConstant
import com.photosurfer.android.core.constant.PushSettingConstant.PUSH_MAIN
import com.photosurfer.android.core.util.DateUtil.dotDateFormatter
import com.photosurfer.android.core.util.Event
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.request.DomainPushSettingRequest
import com.photosurfer.android.domain.repository.PushSettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class PushSettingViewModel @Inject constructor(
    private val pushSettingRepository: PushSettingRepository
) : BaseViewModel() {

    private val _alarmDate = MutableLiveData<String>()
    val alarmDate: LiveData<String> = _alarmDate

    private val _fragmentState = MutableLiveData<PushSettingConstant>(PUSH_MAIN)
    val fragmentState: LiveData<PushSettingConstant> = _fragmentState

    private val _wholeTagList = MutableLiveData<MutableList<TagInfo>>()
    val wholeTagList: LiveData<MutableList<TagInfo>> = _wholeTagList

    private val _representTagIdList = MutableLiveData<MutableList<TagInfo>>()
    val representTagIdList: LiveData<MutableList<TagInfo>> = _representTagIdList

    private val _tempRepresentTagIdList = MutableLiveData<MutableList<TagInfo>>()
    val tempRepresentTagIdList: LiveData<MutableList<TagInfo>> = _tempRepresentTagIdList

    private val _photoId = MutableLiveData<Int>()
    val photoId: LiveData<Int> = _photoId

    private val _pushId = MutableLiveData<Int>()
    val pushId: LiveData<Int> = _pushId

    private val _clickableState = MutableLiveData<Boolean>()
    val clickableState: LiveData<Boolean> = _clickableState

    val memo = MutableLiveData("")

    private val _pushSettingSuccess = MutableLiveData<Event<Boolean>>()
    val pushSettingSuccess: LiveData<Event<Boolean>> = _pushSettingSuccess

    private val _pushSettingFailure = MutableLiveData<Event<Boolean>>()
    val pushSettingFailure: LiveData<Event<Boolean>> = _pushSettingFailure

    fun updateClickableState(state: Boolean) {
        _clickableState.value = state
    }

    fun updatePushId(pushId :Int){
        _pushId.postValue(pushId)
    }

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

    fun updateWholeTagList(wholeTag: MutableList<TagInfo>) {
        _wholeTagList.value = wholeTag
    }

    fun initRepresentTagIdList() {
        if ((_wholeTagList.value?.size ?: 1) < 3) {
            _representTagIdList.postValue(wholeTagList.value)
        } else {
            _representTagIdList.postValue(
                wholeTagList.value?.subList(0, 3)
            )
        }
    }

    fun saveRepresentTagIdList() {
        _representTagIdList.value = tempRepresentTagIdList.value
    }

    fun updateTempRepresentTagList(tempList: MutableList<TagInfo>) {
        _tempRepresentTagIdList.postValue(tempList)
    }

    fun updatePhotoId(photoId: Int) {
        _photoId.postValue(photoId)
    }

    fun postPushSetting() {
        viewModelScope.launch {
            pushSettingRepository.postPushSetting(
                photoId.value ?: throw IllegalStateException(),
                DomainPushSettingRequest(
                    alarmDate.value ?: throw IllegalStateException(),
                    requireNotNull(representTagIdList.value?.map { it.id }).toMutableList(),
                    memo.value ?: throw IllegalStateException()
                )
            ).onSuccess {
                _pushSettingSuccess.postValue(Event(true))
            }.onFailure {
                _pushSettingFailure.postValue(Event(false))
            }
        }
    }
}
