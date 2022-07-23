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
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.SavedTag
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.databinding.FragmentTagBinding
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TagFragment : BaseFragment<FragmentTagBinding>(R.layout.fragment_tag) {

    private lateinit var tagListAdapter: TagListAdapter
    private val tagViewModel: TagViewModel by viewModels()

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTagAdapter()

        tagViewModel.getSavedTagList()
        tagViewModel.savedBookmarkedTagList.observe(viewLifecycleOwner) {
            tagListAdapter.submitList(tagViewModel.savedBookmarkedTagList.value)
        }
        tagViewModel.savedNotBookmarkedTagList.observe(viewLifecycleOwner) {
            tagListAdapter.submitList(tagViewModel.savedNotBookmarkedTagList.value)
        }
    }

    private fun navigateSearchResultActivity(savedTag: SavedTag) {
        val tag: TagInfo = TagInfo(savedTag.id, savedTag.name)
        mainNavigator.navigateSearchResult(requireContext(), listOf(tag))
    }

    private fun setStar() {

    }

    private fun initTagAdapter() {
        tagListAdapter = TagListAdapter(::performOptionsMenuClick, ::navigateSearchResultActivity)
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
                    R.id.tag_edit, R.id.tag_delete -> {

                        PhotoSurferSnackBar.make(
                            binding.root,
                            PhotoSurferSnackBar.SERVICE_PREPARING
                        ).show()
                        // TODO : 삭제 팝업 올라옴
                        return true
                    }
                }
                return false
            }
        })
        popupMenu.show()
    }
}