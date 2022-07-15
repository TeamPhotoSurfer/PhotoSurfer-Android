package com.photosurfer.android.register_tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChooseTagViewModel : ViewModel() {

    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput

    fun setEmptyInput(value: Int) {
        _isEmptyInput.value = value
    }

}