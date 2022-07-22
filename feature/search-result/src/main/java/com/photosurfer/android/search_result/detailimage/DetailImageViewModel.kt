package com.photosurfer.android.search_result.detailimage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : BaseViewModel() {

    private val _tagInfoList = MutableLiveData<List<TagInfo>>()
    val tagInfoList: LiveData<List<TagInfo>> = _tagInfoList

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _photoId = MutableLiveData<Int>()
    val photoId: LiveData<Int> = _photoId

    fun updateTagInfoList(tags: List<TagInfo>) {
        _tagInfoList.value = tags
    }

    fun updateImageUrl(imageUrl: String) {
        _imageUrl.value = imageUrl
    }

    fun updatePhotoId(photoId: Int) {
        _photoId.value = photoId
    }

    fun getDetailImageInfo() {
        viewModelScope.launch {
            imageRepository.getDetailImageInfo(photoId.value ?: -1)
                .onSuccess {
                    updateImageUrl(it.imageUrl)
                    updatePhotoId(it.photoId)
                    updateTagInfoList(it.tagList)
                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_getDetailImageInfo")
                }
        }
    }
}
