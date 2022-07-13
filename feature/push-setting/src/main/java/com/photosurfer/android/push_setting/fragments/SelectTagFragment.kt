package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.view.View
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.push_setting.R
import com.photosurfer.android.push_setting.databinding.FragmentSelectTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTagFragment : BaseFragment<FragmentSelectTagBinding>(R.layout.fragment_select_tag) {

    private lateinit var selectTagListAdapter: SelectTagListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSelectTagListAdapter()
    }

    private fun initSelectTagListAdapter() {
        selectTagListAdapter = SelectTagListAdapter()
        binding.rvRepresentTag.adapter = selectTagListAdapter
    }
}
