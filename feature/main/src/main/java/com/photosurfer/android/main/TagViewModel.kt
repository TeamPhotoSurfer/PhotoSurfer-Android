package com.photosurfer.android.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.SavedTag
import com.photosurfer.android.domain.repository.ChooseTagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val chooseTagRepository: ChooseTagRepository
) : BaseViewModel() {

    private val _savedBookmarkedTagList = MutableLiveData<MutableList<SavedTag>>()
    val savedBookmarkedTagList: MutableLiveData<MutableList<SavedTag>> = _savedBookmarkedTagList

    private val _savedNotBookmarkedTagList = MutableLiveData<MutableList<SavedTag>>()
    val savedNotBookmarkedTagList: MutableLiveData<MutableList<SavedTag>> =
        _savedNotBookmarkedTagList

    private var _editNewTagName = String()
    val editNewTagName: String = _editNewTagName

    fun getSavedTagList() {
        viewModelScope.launch {
            chooseTagRepository.getSavedTagList()
                .onSuccess {
                    _savedBookmarkedTagList.value = it.bookmarked
                    _savedNotBookmarkedTagList.value = it.notBookmarked
                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_getSavedTagList")
                }
        }
    }

    fun putEditTagName() {
        viewModelScope.launch {
            chooseTagRepository.putNewTagName()
                .onSuccess {
                    _editNewTagName = it
                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_putEditTagName")
                }
        }
    }

}