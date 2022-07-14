package com.photosurfer.android.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        setChip()
    }

    private fun setChip() {
        for (i in 0 until viewModel.fakeOftenTagList.size) {
            ChipUnSelectableUtil(requireContext()).make(
                binding.cgOftenSearch,
                viewModel.fakeOftenTagList[i].name
            )
        }
    }
}