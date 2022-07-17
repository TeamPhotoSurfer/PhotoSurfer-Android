package com.photosurfer.android.main

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        setChip()
        onClickEditText()
//        binding.cgOftenSearch.setOnClickListener {
//            Log.d(TAG, "onViewCreated: ${binding.cgOftenSearch.isSelected}")
//        }
    }

    private fun setChip() {
        val tagList = viewModel.fakeOftenTagList
        for (element in tagList) {
            ChipUnSelectableUtil(requireContext()).make(
                binding.cgOftenSearch,
                element.name,
                ::onClickChip
            )
        }
    }

    fun isChipSelected() {
//        for (i in viewModel.fakeOftenTagList.size)
//            if (binding.cgOftenSearch[i].is)
    }

    fun onClickChip() {
        Timber.d("on click on HomeFragment ${binding.cgOftenSearch.checkedChipIds}")
    }

    private fun onClickEditText() {
        binding.etEnterTag.setOnClickListener {
            Timber.tag(TAG).d("onClickEditText: ")
        }
    }
}