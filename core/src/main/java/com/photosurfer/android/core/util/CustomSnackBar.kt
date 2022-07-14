package com.photosurfer.android.core.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.photosurfer.android.core.R
import com.photosurfer.android.shared.R.layout.custom_snackbar
import com.photosurfer.android.shared.databinding.CustomSnackbarBinding

class CustomSnackBar (view: View, private val message: String) {

    companion object {

        fun make(view: View, message: String) = CustomSnackBar(view, message)
    }

    private val context = view.context
    private val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout

    private val inflater = LayoutInflater.from(context)
    private val snackbarBinding: CustomSnackbarBinding = DataBindingUtil.inflate(inflater, custom_snackbar, null, false)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackbarLayout) {
            removeAllViews()
            setPadding(20, 0, 20, 38)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(snackbarBinding.root, 0)
        }
    }

    private fun initData() {
        snackbarBinding.tvCustomSnackBar.text = message
    }

    fun show() {
        snackbar.show()
    }
}
