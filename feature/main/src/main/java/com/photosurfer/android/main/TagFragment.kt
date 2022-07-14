package com.photosurfer.android.main

import android.R
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
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
        tagListAdapter = TagListAdapter(object : TagListAdapter.OptionsMenuClickListener {
            override fun onOptionsMenuClicked(position: Int) {
                performOptionsMenuClick(position)
            }

        })
        binding.rcvTagList.adapter = tagListAdapter
        tagListAdapter.tagList.addAll(
            mutableListOf(
                TagInfo(1, "tag1"),
                TagInfo(1, "tag2"),
                TagInfo(1, "tag3"),
                TagInfo(1, "tag4"),
                TagInfo(1, "tag5"),
                TagInfo(1, "tag6"),
            )
        )
        tagListAdapter.notifyDataSetChanged()
    }

    private fun performOptionsMenuClick(position: Int) {

        val wrapper: Context = ContextThemeWrapper(requireContext(), R.style.popupMenuStyle)
//        val popup = PopupMenu(wrapper, sourceView)

        val popupMenu = PopupMenu(wrapper, binding.rcvTagList[position].findViewById(R.id.iv_threedots), Gravity.RIGHT)
        popupMenu.inflate(R.menu.tag_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId) {
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