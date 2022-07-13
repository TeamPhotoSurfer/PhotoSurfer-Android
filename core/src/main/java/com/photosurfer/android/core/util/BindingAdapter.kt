package com.photosurfer.android.core.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:goneUnless")
fun View.visibility(boolean: Boolean) {
    this.visibility = if (boolean) View.GONE else View.VISIBLE
}