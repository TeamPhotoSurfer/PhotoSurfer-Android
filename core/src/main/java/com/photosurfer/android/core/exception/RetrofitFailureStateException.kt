package com.photosurfer.android.core.exception

class RetrofitFailureStateException(error: String?, val code: Int) : Exception(error)
