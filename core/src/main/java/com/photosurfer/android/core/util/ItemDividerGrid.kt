package com.photosurfer.android.core.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class ItemDividerGrid(
    private val numberOfColumns: Int,
    private val rowSpacingDP: Float = 0f,
    private val columnSpacingDP: Float = 0f,
    private val edgeSpacingVerticalDP: Float = 0f,
    private val edgeSpacingHorizontalDP: Float = 0f
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val numberOfRows = (parent.adapter?.itemCount ?: -1) / numberOfColumns
        val column = position % numberOfColumns
        val row = position / numberOfColumns
        val context = view.context
        ///horizontal
        when (column) {
            0 -> {
                outRect.left = convertDpToPixel(edgeSpacingVerticalDP, context)
                outRect.right = convertDpToPixel(columnSpacingDP / 2, context)
            }
            numberOfColumns - 1 -> {
                outRect.left = convertDpToPixel(columnSpacingDP / 2, context)
                outRect.right = convertDpToPixel(edgeSpacingVerticalDP, context)
            }
            else -> {
                outRect.left = convertDpToPixel(columnSpacingDP / 2, context)
                outRect.right = convertDpToPixel(columnSpacingDP / 2, context)
            }
        }
        //vertical
        when (row) {
            0 -> {
                outRect.top = convertDpToPixel(edgeSpacingHorizontalDP, context)
                outRect.bottom = convertDpToPixel(rowSpacingDP / 2, context)
            }
            numberOfRows -> {
                outRect.top = convertDpToPixel(rowSpacingDP / 2, context)
                outRect.bottom = convertDpToPixel(edgeSpacingHorizontalDP, context)
            }
            else -> {
                outRect.top = convertDpToPixel(rowSpacingDP / 2, context)
                outRect.bottom = convertDpToPixel(rowSpacingDP / 2, context)
            }
        }
    }

    fun convertDpToPixel(dp: Float, context: Context?): Int {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        } else {
            val metrics = Resources.getSystem().displayMetrics
            (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }
    }
}