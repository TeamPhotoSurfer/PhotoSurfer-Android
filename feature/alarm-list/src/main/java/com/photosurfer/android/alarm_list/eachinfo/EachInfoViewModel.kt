package com.photosurfer.android.alarm_list.eachinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EachInfoViewModel @Inject constructor() : BaseViewModel() {

    private val _fragmentState = MutableLiveData<String>()
    val fragmentState: LiveData<String> = _fragmentState

    private val _imgUrl = MutableLiveData<String>()
    val imgurl: LiveData<String> = _imgUrl

    private val _pushId = MutableLiveData<Int>()
    val pushId: LiveData<Int> = _pushId

    fun updateImgUrl(imgUrl: String) {
        _imgUrl.postValue(imgUrl)
    }

    fun updatePushId(pushId: Int) {
        _pushId.postValue(pushId)
    }

    fun updateFragmentState(state: String) {
        _fragmentState.postValue(state)
    }
}
