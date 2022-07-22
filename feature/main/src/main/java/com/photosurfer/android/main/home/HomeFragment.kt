package com.photosurfer.android.main.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.constant.DialogMode
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.core.util.PhotoSurferDialogUtil
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.R
import com.photosurfer.android.main.databinding.FragmentHomeBinding
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        getOftenSearchTagList()
        setChipAfterTagDataExist()
        onClickEditText()

        val dialog =
            PhotoSurferDialogUtil(DialogMode.DELETE_SELECTED_PHOTO.name, ::dismissBottomSheet, "인턴")
        dialog.show(requireActivity().supportFragmentManager, this.javaClass.name)
    }

    private fun dismissBottomSheet() {

    }

    private fun getOftenSearchTagList() {
        viewModel.getOftenSearchTagList()
    }

    private fun setChipAfterTagDataExist() {
        viewModel.fakeOftenTagList.observe(requireActivity()) {
            setChip()
            initChipClickListener()
        }
    }

    private fun setChip() {
        for (tag in viewModel.fakeOftenTagList.value ?: return) {
            val chip = ChipUnSelectableUtil(requireContext()).getChip(tag.name)
            binding.cgOftenSearch.addView(chip)
        }
    }

    private fun navigateToSearchTagActivity(tag: TagInfo? = null) {
        mainNavigator.navigateSearchTag(requireContext(), tag)
        Timber.tag(TAG).d("onClick: ${tag?.name}")
    }

    private fun initChipClickListener() {
        val listSize: Int = viewModel.fakeOftenTagList.value?.size ?: return
        for (i in 0 until listSize) {
            binding.cgOftenSearch[i].setOnClickListener {
                val tag = requireNotNull(viewModel.fakeOftenTagList.value)[i]
                navigateToSearchTagActivity(tag)
            }
        }
    }

    private fun onClickEditText() {
        binding.etEnterTag.setOnClickListener {
            navigateToSearchTagActivity()
        }
    }
}