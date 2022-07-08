package com.photosurfer.android.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.TriangleEdgeTreatment
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.ext.getColor
import com.photosurfer.android.core.util.addHashTag
import com.photosurfer.android.main.databinding.FragmentHomeBinding
import com.photosurfer.android.shared.R.color
import com.photosurfer.android.shared.R.style.body2

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        setChip()
    }

    private fun setChip() {

        val states = arrayOf(
            intArrayOf(android.R.attr.state_selected),
            intArrayOf(-android.R.attr.state_selected),
        )
        val backgroundColors = intArrayOf(getColor(color.sub), getColor(color.sub))
        val textColors = intArrayOf(getColor(color.main), getColor(color.main))
        val strokeColors =
            intArrayOf(getColor(android.R.color.transparent), getColor(android.R.color.transparent))
        val backgroundStateList = ColorStateList(states, backgroundColors)
        val textStateList = ColorStateList(states, textColors)
        val strokeStateList = ColorStateList(states, strokeColors)

        for (i in 0 until viewModel.fakeOftenTagList.size) {
            val chip = Chip(requireContext()).apply {
                this.text = addHashTag(viewModel.fakeOftenTagList[i].name)
                this.chipStartPadding = 30F
                this.chipEndPadding = 30F
                this.chipStrokeColor = strokeStateList
                this.chipBackgroundColor = backgroundStateList
                this.setTextColor(textStateList)
                this.setTextAppearance(body2)
                this.setRippleColorResource(android.R.color.transparent)
            }
            chip.shapeAppearanceModel = chip.shapeAppearanceModel.toBuilder()
                .setLeftEdge(TriangleEdgeTreatment(25F, true))
                .setTopRightCorner(CornerFamily.ROUNDED, 70F)
                .setBottomRightCorner(CornerFamily.ROUNDED, 70F)
                .build()
            binding.cgOftenSearch.addView(chip)
        }
    }
}