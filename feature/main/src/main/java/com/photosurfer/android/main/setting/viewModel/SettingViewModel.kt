package com.photosurfer.android.main.setting.viewModel

import androidx.lifecycle.MutableLiveData
import com.photosurfer.android.core.base.BaseViewModel

class SettingViewModel : BaseViewModel() {
    val userName = "포토서퍼"
    val email = "photosurfer@gmail.com"
    val isPushOn: MutableLiveData<Boolean> = MutableLiveData(true)
    val isAutoLogin: MutableLiveData<Boolean> = MutableLiveData(true)

}