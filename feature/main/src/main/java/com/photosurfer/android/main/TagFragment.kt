package com.photosurfer.android.main

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.databinding.FragmentTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TagFragment : BaseFragment<FragmentTagBinding>(R.layout.fragment_tag) {

    private lateinit var tagListAdapter: TagListAdapter
    private val tagViewModel: TagViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTagAdapter()

        tagViewModel.getSavedTagList()
        tagViewModel.savedBookmarkedTagList.observe(viewLifecycleOwner) {
            tagListAdapter.submitList(tagViewModel.savedBookmarkedTagList.value)
        }
        tagViewModel.savedNotBookmarkedTagList.observe(viewLifecycleOwner){
            tagListAdapter.submitList(tagViewModel.savedNotBookmarkedTagList.value)
        }
    }

    private fun initTagAdapter() {
        tagListAdapter = TagListAdapter(::performOptionsMenuClick)
        binding.rcvTagList.adapter = tagListAdapter
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