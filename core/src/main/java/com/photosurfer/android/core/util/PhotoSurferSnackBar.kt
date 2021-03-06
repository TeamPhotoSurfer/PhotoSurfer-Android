package com.photosurfer.android.core.util

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.photosurfer.android.core.ext.getString
import com.photosurfer.android.shared.R.layout.custom_snackbar
import com.photosurfer.android.shared.R.string
import com.photosurfer.android.shared.databinding.CustomSnackbarBinding

class PhotoSurferSnackBar(view: View, private val usage: Int) {

    companion object {

        fun make(view: View, usage: Int) = PhotoSurferSnackBar(view, usage)

        // 사용처 정리
        const val SELECT_TAG_FRAGMENT = 0
        const val PUSH_MAIN_FRAGMENT = 1
        const val PUSH_MAIN_NETWORK_ERROR = 2
        const val SETTING_ACTIVITY = 3
        const val CHOOSE_TAG_FRAGMENT = 4
        const val SERVICE_PREPARING = 5
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
            PUSH_MAIN_FRAGMENT, SETTING_ACTIVITY, SERVICE_PREPARING -> view.getString(string.push_main_fragment)
            PUSH_MAIN_NETWORK_ERROR -> "오류가 났습니다 다시 시도해 주세요"
            CHOOSE_TAG_FRAGMENT -> view.getString(string.choose_tag_fragment)
            else -> throw IllegalStateException()
        }
    }

    fun show() {
        snackbar.show()
    }
}
