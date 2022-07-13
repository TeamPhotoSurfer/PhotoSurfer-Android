package com.photosurfer.android.register_tag

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.photosurfer.android.shared.R.color
import com.google.android.material.chip.Chip
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.ext.getColor
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding
import com.photosurfer.android.shared.R.style.body2

class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isEmpty = true
        setChips()
        checkInputNum()
        checkPlatform()
    }

    private fun checkPlatform() {
        binding.clCheckPlatform.setOnClickListener {
            binding.ivCheckPlatform.isSelected = binding.ivCheckPlatform.isSelected != true
            binding.tvCheckPlatform.isSelected = binding.tvCheckPlatform.isSelected != true
        }
    }

    private fun checkInputNum() {
        binding.etTag.addTextChangedListener {
            binding.isEmpty = binding.etTag.text.isEmpty()
        }
    }

    private fun setChips() {

        val states = arrayOf(
            intArrayOf(-android.R.attr.state_selected),
            intArrayOf(android.R.attr.state_selected),
        )
        val backgroundColors = intArrayOf(getColor(color.point_sub), getColor(color.point_main))
        val textColors = intArrayOf(getColor(color.point_main), getColor(color.white))
        val backgroundStateList = ColorStateList(states, backgroundColors)
        val textStateList = ColorStateList(states, textColors)

        for (i in 0 until Category.values().size) {
            binding.cgRecent.addView(
                Chip(requireContext()).apply {
                    this.text = "# " + Category.values()[i].categoryName
                    this.chipBackgroundColor = backgroundStateList
                    this.setTextColor(textStateList)
                    this.setTextAppearance(body2)
                    this.setRippleColorResource(android.R.color.transparent)
                }
            )
        }

        for (i in 0 until Category.values().size) {
            binding.cgOften.addView(
                Chip(requireContext()).apply {
                    this.text = "# " + Category.values()[i].categoryName
                    this.chipBackgroundColor = backgroundStateList
                    this.setTextColor(textStateList)
                    this.setTextAppearance(body2)
                    this.setRippleColorResource(android.R.color.transparent)
                }
            )
        }

        for (i in 0 until Category.values().size) {
            binding.cgPlatform.addView(
                Chip(requireContext()).apply {
                    this.text = "# " + Category.values()[i].categoryName
                    this.chipBackgroundColor = backgroundStateList
                    this.setTextColor(textStateList)
                    this.setTextAppearance(body2)
                    this.setRippleColorResource(android.R.color.transparent)
                }
            )
        }
    }

    enum class Category(val categoryName: String) {
        PHOTOSURFER("포토서퍼"),
        CAFE("카페"),
        LIVETIP("생활꿀팁"),
        WISHLIST("위시리스트"),
        PLAN("휴학계획"),
        TRAVEL("여행");
    }

    enum class TagDataExample(val tag: String) {
        T("t"),
        TE("te"),
        TES("tes"),
        TEST("test");
    }
}