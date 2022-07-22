package com.photosurfer.android.main.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.R
import com.photosurfer.android.main.databinding.FragmentHomeBinding
import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.register_tag.PointSubTagAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var oftenTagAdapter: PointSubTagAdapter

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        initAdapter()
        initRecyclerViewLayout()
        getOftenSearchTagList()
        setChipAfterTagDataExist()
        onClickEditText()
    }

    private fun initRecyclerViewLayout() {
        val oftenLayoutManager = FlexboxLayoutManager(context)
        oftenLayoutManager.flexWrap = FlexWrap.WRAP
        binding.rvOften.layoutManager = oftenLayoutManager
    }

    private fun getOftenSearchTagList() {
        viewModel.getOftenSearchTagList()
    }

    private fun setChipAfterTagDataExist() {
        viewModel.oftenTagList.observe(requireActivity()) {
            oftenTagAdapter.submitList(viewModel.oftenTagList.value)
        }
    }

    private fun initAdapter() {
        oftenTagAdapter = PointSubTagAdapter(::selectTag)
        binding.rvOften.adapter = oftenTagAdapter
    }

    private fun selectTag(tagInfo: TagInfo) {
        navigateToSearchTagActivity(tagInfo)
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