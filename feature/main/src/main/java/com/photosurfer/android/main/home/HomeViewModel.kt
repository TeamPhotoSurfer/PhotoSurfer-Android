package com.photosurfer.android.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.repository.TagListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val tagListRepository: TagListRepository
) : ViewModel() {

    val isOftenTagMoreThanZero = true
    private val _fakeOftenTagList = MutableLiveData<List<TagInfo>>()
    val fakeOftenTagList: LiveData<List<TagInfo>> = _fakeOftenTagList

    val fakeOftenTagLongList = listOf(
        TagInfo(0, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(1, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(2, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(3, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(4, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(5, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼")
    )

    fun getOftenSearchTagList() {
        viewModelScope.launch {
            tagListRepository.getOftenSearchTagList()
                .onSuccess {
                    updateOftenSearchTagList(it)
                }
                .onFailure {
                    Timber.d(it, "${this.javaClass.name}_getOftenSearchTag")
                }
        }
    }

    private fun updateOftenSearchTagList(list: List<TagInfo>) {
        _fakeOftenTagList.value = list
    }
}