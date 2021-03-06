package com.photosurfer.android.core.util

import android.content.Context
import android.content.res.ColorStateList
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.TriangleEdgeTreatment
import com.photosurfer.android.shared.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChipCancelableUtil @Inject constructor(@ApplicationContext private val context: Context) {

    private val states = arrayOf(
        intArrayOf(android.R.attr.state_selected),
        intArrayOf(-android.R.attr.state_selected),
    )
    private val backgroundColors =
        intArrayOf(context.getColor(R.color.main), context.getColor(R.color.main))
    private val textColors =
        intArrayOf(context.getColor(R.color.white), context.getColor(R.color.white))
    private val strokeColors =
        intArrayOf(
            context.getColor(android.R.color.transparent),
            context.getColor(android.R.color.transparent)
        )
    private val backgroundStateList = ColorStateList(states, backgroundColors)
    private val textStateList = ColorStateList(states, textColors)
    private val strokeStateList = ColorStateList(states, strokeColors)

    private fun getChip(tag: String, onChipClick: (() -> Unit)): Chip {
        val chip = Chip(context).apply {
            this.text = tag
            this.setTextColor(textStateList)
            this.setTextAppearance(R.style.body2)
            this.chipStrokeColor = strokeStateList
            this.chipBackgroundColor = backgroundStateList
            this.iconEndPadding = 0F
            this.closeIconTint = textStateList
            this.isCloseIconVisible = true
            this.closeIcon = getDrawable(context, R.drawable.ic_x_circle_fill)
            this.textStartPadding = 0F
            this.setRippleColorResource(android.R.color.transparent)
            this.isCheckable = true
            this.isChecked = true
            this.chipStartPadding = 38F
            this.closeIconSize = 60F
            this.chipIconSize = 60F
            this.chipIcon = getDrawable(context, R.drawable.ic_hashtag_line_white)
        }

        chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder()
            .setLeftEdge(TriangleEdgeTreatment(25F, true))
            .setTopRightCorner(CornerFamily.ROUNDED, 70F)
            .setBottomRightCorner(CornerFamily.ROUNDED, 70F)
            .build()

        return chip
    }

    fun make(view: ChipGroup, chipName: String, onChipClick: (() -> Unit)) {
        val chip = getChip(chipName, onChipClick)
        chip.setOnClickListener {
            view.removeView(it)
            onChipClick()
        }
        view.addView(chip)
    }
}