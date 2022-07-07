package com.photosurfer.android.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.core.util.Event
import kotlinx.coroutines.CoroutineExceptionHandler
import java.security.cert.CertificateException

class BaseViewModel : ViewModel() {

    private val _moveToLogin = MutableLiveData<Event<Boolean>>()
    val moveToLogin: LiveData<Event<Boolean>> = _moveToLogin

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is CertificateException -> _moveToLogin.postValue(Event(true))
        }
    }
}
