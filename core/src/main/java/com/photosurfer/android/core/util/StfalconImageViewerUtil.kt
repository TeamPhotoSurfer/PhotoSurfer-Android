package com.photosurfer.android.core.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stfalcon.imageviewer.StfalconImageViewer

object StfalconImageViewerUtil {
    fun initImageViewer(
        context: Context,
        startImageView: ImageView,
        ImageList: List<String>,
        startPosition: Int
    ) {
        StfalconImageViewer.Builder<String>(context, ImageList) { view, image ->
            Glide.with(context).load(image).into(view)
        }.withTransitionFrom(startImageView).withStartPosition(startPosition).show()
    }
}
