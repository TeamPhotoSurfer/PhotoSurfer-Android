package com.photosurfer.android.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.repository.ChooseTagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchTagViewModel @Inject constructor(
    private val chooseTagRepository: ChooseTagRepository
) : BaseViewModel() {

    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput
    var inputList: MutableList<TagInfo> = mutableListOf()

    private val _recentList = MutableLiveData<MutableList<TagInfo>>()
    val recentList: MutableLiveData<MutableList<TagInfo>> = _recentList

    private val _oftenList = MutableLiveData<MutableList<TagInfo>>()
    val oftenList: MutableLiveData<MutableList<TagInfo>> = _oftenList

    private val _platformList = MutableLiveData<MutableList<TagInfo>>()
    val platformList: MutableLiveData<MutableList<TagInfo>> = _platformList

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

    fun setEmptyInput(value: Int) {
        _isEmptyInput.value = value
    }

    fun selectTag(item: TagInfo) {
        inputList.add(item)
    }

    fun deleteTag(item: TagInfo) {
        inputList.remove(item)
    }

}