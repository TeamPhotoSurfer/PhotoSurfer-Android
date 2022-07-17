package com.photosurfer.android.main

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.get
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.databinding.FragmentTagBinding


class TagFragment : BaseFragment<FragmentTagBinding>(R.layout.fragment_tag) {

    private lateinit var tagListAdapter: TagListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTagAdapter()
    }

    private fun initTagAdapter() {
        tagListAdapter = TagListAdapter(::performOptionsMenuClick)

        binding.rcvTagList.adapter = tagListAdapter
        tagListAdapter.submitList(
            mutableListOf(
                TagInfo(1, "tag1"),
                TagInfo(1, "tag2"),
                TagInfo(1, "tag3"),
                TagInfo(1, "tag4tag4tag4tag4tag4tag4tag4tag4tag4tag4tag4tag4tag4tag4"),
                TagInfo(1, "tag5"),
                TagInfo(1, "tag66666666"),
                TagInfo(1, "tag666666667"),
                TagInfo(1, "tag666666668"),
                TagInfo(1, "tag666666669"),
                TagInfo(1, "tag10"),
                TagInfo(1, "tag11"),
                TagInfo(1, "tag666666667"),
                TagInfo(1, "tag666666668"),
                TagInfo(1, "tag666666669"),
                TagInfo(1, "tag10"),
                TagInfo(1, "tag11"),
                TagInfo(1, "tag666666667"),
                TagInfo(1, "tag666666668"),
                TagInfo(1, "tag666666669"),
                TagInfo(1, "tag10"),
                TagInfo(1, "tag11"),
                TagInfo(1, "tag666666667"),
                TagInfo(1, "tag666666668"),
                TagInfo(1, "tag666666669"),
                TagInfo(1, "tag10"),
                TagInfo(1, "tag11")
            )
        )
    }

    private fun performOptionsMenuClick(position: Int, threeDot: ImageView) {

        val wrapper: Context = ContextThemeWrapper(
            requireContext(),
            com.photosurfer.android.shared.R.style.popupMenuStyle
        )

        val popupMenu = PopupMenu(wrapper, threeDot, Gravity.RIGHT)
        popupMenu.inflate(R.menu.tag_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.tag_edit -> {
                        return true
                    }
                    R.id.tag_delete -> {
                        return true
                    }
                }
                return false
            }

        })
        popupMenu.show()
    }
}