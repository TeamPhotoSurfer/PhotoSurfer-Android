package com.photosurfer.android.core.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.photosurfer.android.core.R
import com.photosurfer.android.core.ext.getString
import com.photosurfer.android.shared.R.*
import com.photosurfer.android.shared.R.layout.custom_snackbar
import com.photosurfer.android.shared.databinding.CustomSnackbarBinding

class PhotoSurferSnackBar(view: View, private val usage: Int) {

    companion object {

        fun make(view: View, usage: Int) = PhotoSurferSnackBar(view, usage)

        // 사용처 정리
        const val SELECT_TAG_FRAGMENT = 0
        const val PUSH_MAIN_FRAGMENT = 1
    }

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
    private val inflater = LayoutInflater.from(context)
    private val snackbarBinding: CustomSnackbarBinding =
        DataBindingUtil.inflate(inflater, custom_snackbar, null, false)

    init {
        initView()
        initData(view)
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setPadding(20, 0, 20, 38)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackbarBinding.root, 0)
        }
    }

    private fun initData(view: View) {
        snackbarBinding.snackBarText = when (usage) {
            SELECT_TAG_FRAGMENT -> view.getString(string.select_tag_fragment)
            PUSH_MAIN_FRAGMENT -> view.getString(string.push_main_fragment)
            else -> throw IllegalStateException()
        }
    }

    fun show() {
        snackbar.show()
    }
}
