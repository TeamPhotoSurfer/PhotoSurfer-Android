package com.photosurfer.android.search_result.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.domain.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    private var _originTagList = MutableLiveData<MutableList<TagInfo>>()
    val originTagList: LiveData<MutableList<TagInfo>> = _originTagList
    fun setOriginTagList(tagList: List<TagInfo>) {
        _originTagList.value = tagList.toMutableList()
    }

    private var _tagList: MutableLiveData<MutableList<TagInfo>> =
        MutableLiveData<MutableList<TagInfo>>()
    val tagList: LiveData<MutableList<TagInfo>> = _tagList
    val isTagListEmpty = MutableLiveData(tagList.value?.size == 0)
    fun setTempTagList(tagList: List<TagInfo>) {
        _tagList.value = tagList.toMutableList()
    }

    private var _thumbnailList = MutableLiveData<MutableList<ThumbnailInfo?>>()
    val thumbnailList: LiveData<MutableList<ThumbnailInfo?>> = _thumbnailList
    val noThumbnailData = MutableLiveData(thumbnailList.value?.size == 0)

    val selectedThumbnailList = mutableListOf<ThumbnailInfo>()
    var selectedTempThumbnailList = mutableListOf<ThumbnailInfo>()
    var selectedThumbnailListPosition = mutableListOf<Int>()

    fun clearCheckedTempThumbnail() {
        selectedTempThumbnailList.forEach { it.isChecked = false }
    }

    fun updateSelectedThumbnailList(thumbnailInfo: ThumbnailInfo, position: Int) {
        if (thumbnailInfo.isChecked)
            selectedThumbnailListPosition.add(position)
        else selectedThumbnailListPosition.remove(position)
    }

//    fun copySelectedThumbnailList() {
//        selectedTempThumbnailList = selectedThumbnailList.toMutableList()   // deep copy
//    }
//
//    private fun addOnThumbnailList(thumbnail: ThumbnailInfo) {
//        selectedThumbnailList.add(thumbnail)
//    }
//
//    fun deleteOnThumbnailList(thumbnail: ThumbnailInfo) {
//        selectedThumbnailList.remove(thumbnail)
//    }

    fun clearCheckedThumbnail() {
        thumbnailList.value?.forEach { it?.isChecked = false }
    }

    fun getPhotosByTags() {
        viewModelScope.launch {
            Log.d(TAG, "getPhotosByTags: ${tagList.value}")
            val option = mutableListOf<Pair<String, Int>>()
            tagList.value?.forEach { option.add(Pair("id", it.id)) }
            Log.d(TAG, "getPhotosByTags: $option")
            Log.d(TAG, "getPhotosByTags: $tagList")
            photoRepository.getPhotoListByTag(option)
                .onSuccess {
                    _thumbnailList.value = it.photos.toMutableList()
                    Log.d(TAG, "getPhotosByTags: $it")
                }.onFailure {
                    _thumbnailList.value = emptyArray<ThumbnailInfo>().toMutableList()
                    Timber.d(it, "$/{this.javaClass.name}_getPhotosByTags")
                }.run {
                    noThumbnailData.value = thumbnailList.value?.size == 0
                }
        }
    }

    fun deleteTag(position: Int) {
        _tagList.value?.removeAt(position)
        isTagListEmpty.value = tagList.value?.size == 0
    }

}