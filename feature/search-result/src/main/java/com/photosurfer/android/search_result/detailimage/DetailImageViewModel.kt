package com.photosurfer.android.search_result.detailimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.util.Event
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

    private val _deleteSuccessState = MutableLiveData<Event<Boolean>>()
    val deleteSuccessState: LiveData<Event<Boolean>> = _deleteSuccessState

    private val _processState = MutableLiveData<String>("START")
    val processState: LiveData<String> = _processState

    fun updateProcessState(processState: String) {
        _processState.value = processState
    }

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

    fun deleteImage() {
        viewModelScope.launch {
            val option = mutableMapOf<String, Int>()
            option["id"] = requireNotNull(photoId.value)
            imageRepository.deleteImage(option)
                .onSuccess {
                    _deleteSuccessState.value = Event(true)
                }.onFailure {
                    Timber.d(it, "${this.javaClass.name}_deleteImage")
                }
        }
    }
}
