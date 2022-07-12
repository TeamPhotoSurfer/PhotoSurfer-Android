package com.photosurfer.android.core.util

import android.view.View
import androidx.databinding.BindingAdapter
import timber.log.Timber

@BindingAdapter("app:goneUnless")
fun View.visibility(boolean: Boolean) {
    Timber.d("login $boolean")
    this.visibility = if (boolean) View.GONE else View.VISIBLE
}