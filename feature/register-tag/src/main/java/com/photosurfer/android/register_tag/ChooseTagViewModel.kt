package com.photosurfer.android.register_tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.util.Event
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.repository.ChooseTagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChooseTagViewModel @Inject constructor(
    private val chooseTagRepository: ChooseTagRepository
) : BaseViewModel() {
    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput

    private val _inputList: MutableList<TagInfo> = mutableListOf()
    val inputList: MutableList<TagInfo> = _inputList

    private val _recentList = MutableLiveData<MutableList<TagInfo>>()
    val recentList: MutableLiveData<MutableList<TagInfo>> = _recentList

    private val _oftenList = MutableLiveData<MutableList<TagInfo>>()
    val oftenList: MutableLiveData<MutableList<TagInfo>> = _oftenList

    private val _platformList = MutableLiveData<MutableList<TagInfo>>()
    val platformList: MutableLiveData<MutableList<TagInfo>> = _platformList

    private val _platformListId = MutableLiveData<MutableList<Int>>()
    val platformListId: MutableLiveData<MutableList<Int>> = _platformListId

    private val _chooseTagSuccess = MutableLiveData<Event<Boolean>>()
    val chooseTagSuccess: LiveData<Event<Boolean>> = _chooseTagSuccess

    private val _chooseTagFailure = MutableLiveData<Event<Boolean>>()
    val chooseTagFailure: LiveData<Event<Boolean>> = _chooseTagFailure

    fun setPlatformIdList() {
        _platformListId.value = platformList.value?.map { it.id }?.toMutableList()
    }

    fun setEmptyInput() {
        _isEmptyInput.value = inputList.size
    }

    fun selectTag(item: TagInfo) {
        inputList.add(item)
    }

    fun deleteTag(item: TagInfo) {
        inputList.remove(item)
    }

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
}
