package com.photosurfer.android.push_setting.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.push_setting.PushSettingViewModel
import com.photosurfer.android.push_setting.R
import com.photosurfer.android.push_setting.databinding.FragmentSelectTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTagFragment : BaseFragment<FragmentSelectTagBinding>(R.layout.fragment_select_tag) {

    private val pushSettingViewModel by activityViewModels<PushSettingViewModel>()
    private lateinit var selectTagListAdapter: SelectTagListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSelectTagListAdapter()
    }

    private fun initSelectTagListAdapter() {
        selectTagListAdapter = SelectTagListAdapter(
            requireNotNull(pushSettingViewModel.representTagIdList.value),
            ::updateTempRepresentTagList
        )
        binding.rvRepresentTag.adapter = selectTagListAdapter
        selectTagListAdapter.submitList(pushSettingViewModel.wholeTagList.value)
    }

    private fun updateTempRepresentTagList(tempList: MutableList<TagInfo>) {
        pushSettingViewModel.updateTempRepresentTagList(tempList)
    }
}
