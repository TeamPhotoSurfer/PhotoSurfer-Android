package com.photosurfer.android.search_result.detailimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.domain.entity.TagInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailImageViewModel @Inject constructor() : BaseViewModel(){

    private val _tagInfoList = MutableLiveData<List<TagInfo>>()
    val tagInfoList: LiveData<List<TagInfo>> = _tagInfoList

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl
}
