package com.photosurfer.android.main.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.ChipUnSelectableUtil
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
    private lateinit var tagList: List<TagInfo>

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        initTagList()
        setChip()
        onClickEditText()
        initChipClickListener()
    }

    private fun initTagList() {
        tagList = viewModel.fakeOftenTagList
    }

    private fun setChip() {
        for (tag in tagList) {
            val chip = ChipUnSelectableUtil(requireContext()).getChip(tag.name)
            binding.cgOftenSearch.addView(chip)
        }
    }

    private fun initChipClickListener() {
        for (i in tagList.indices) {
            binding.cgOftenSearch[i].setOnClickListener {
                val tag = tagList[i]
                navigateToSearchTagActivity(tag)
            }
        }
    }

    private fun navigateToSearchTagActivity(tag: TagInfo? = null) {
        mainNavigator.navigateSearchTag(requireContext(), tag)
        Timber.tag(TAG).d("onClick: ${tag?.name}")
    }

    private fun onClickEditText() {
        binding.etEnterTag.setOnClickListener {
            navigateToSearchTagActivity()
        }
    }
}