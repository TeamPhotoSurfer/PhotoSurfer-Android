package com.photosurfer.android.register_tag

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.util.Event
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest
import com.photosurfer.android.domain.repository.ChooseTagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChooseTagViewModel @Inject constructor(
    private val chooseTagRepository: ChooseTagRepository
) : BaseViewModel() {
    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput

    private val _inputList = MutableLiveData<MutableList<TagInfo>>()
    val inputList: MutableLiveData<MutableList<TagInfo>> = _inputList

    private val _recentList = MutableLiveData<MutableList<TagInfo>>()
    val recentList: MutableLiveData<MutableList<TagInfo>> = _recentList

    private val _oftenList = MutableLiveData<MutableList<TagInfo>>()
    val oftenList: MutableLiveData<MutableList<TagInfo>> = _oftenList

    private val _platformList = MutableLiveData<MutableList<TagInfo>>()
    val platformList: MutableLiveData<MutableList<TagInfo>> = _platformList

    private val _chooseTagSuccess = MutableLiveData<Event<Boolean>>()
    val chooseTagSuccess: LiveData<Event<Boolean>> = _chooseTagSuccess

    private val _chooseTagFailure = MutableLiveData<Event<Boolean>>()
    val chooseTagFailure: LiveData<Event<Boolean>> = _chooseTagFailure


    val imageFile: File
        get() {
            TODO()
        }

    fun setEmptyInput(value: Int) {
        _isEmptyInput.value = value
    }

    fun selectTag(item: TagInfo) {
        inputList.value?.add(item)
    }

    fun deleteTag(item: TagInfo) {
        inputList.value?.remove(item)
    }

    // TODO: GET 서버 로직 붙이고 추가할 예정
//    fun postChooseTag() {
//        viewModelScope.launch {
//            chooseTagRepository.postTag(
//                DomainChooseTagRequest(
//                    // TODO : imageFile 멀티파트 처리한 거 넣어줘야함
//                    file = imageFile,
//                    tags = inputList
//                )
//            ).onSuccess {
//                _chooseTagSuccess.postValue(Event(true))
//            }.onFailure {
//                _chooseTagFailure.postValue(Event(true))
//            }
//        }
//    }

    fun getTagList() {
        viewModelScope.launch {
            chooseTagRepository.getTagList()
                .onSuccess {
                    _recentList.value = it.recentTagList
                    _oftenList.value = it.oftenTagList
                    _platformList.value = it.platformTagList

                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_getTagList")
                }
        }
    }
}
