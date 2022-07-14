package com.photosurfer.android.main

import android.nfc.Tag
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        tagListAdapter = TagListAdapter()
        binding.rcvTagList.adapter = tagListAdapter
        tagListAdapter.tagList.addAll(
            mutableListOf(
                TagInfo(1, "tag1"),
                TagInfo(1, "tag2"),
                TagInfo(1, "tag3")
            )
        )
        tagListAdapter.notifyDataSetChanged()
    }
}