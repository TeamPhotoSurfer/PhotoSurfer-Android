package com.photosurfer.android.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.constant.SELECTED_TAG
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.PointSubTagAdapter
import com.photosurfer.android.register_tag.PointTagAdapter
import com.photosurfer.android.search.databinding.FragmentSearchTagBinding
import timber.log.Timber


class SearchTagFragment : BaseFragment<FragmentSearchTagBinding>(R.layout.fragment_search_tag) {

    private val searchTagViewModel: SearchTagViewModel by viewModels()

    private lateinit var inputTagAdapter: PointTagAdapter
    private lateinit var recentTagAdapter: PointSubTagAdapter
    private lateinit var oftenTagAdapter: PointSubTagAdapter
    private lateinit var platformTagAdapter: PointSubTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true
        searchTagViewModel.setTagList()

        getExtraData()
        onClickBackButton()
        initAdapter()
        setDataOnRecyclerView()
        convertTypingView()
        setCompleteOnKeyBoardListener()
        deleteInput()
        checkInputNum()
        initRecyclerViewLayout()
    }

    private fun getExtraData() {
        val tagInfo = requireActivity().intent.getSerializableExtra(SELECTED_TAG) as? TagInfo
        Timber.d("tagInfo from previous $tagInfo")
        tagInfo?.let { addTagWithInputText(it.name) }
    }

    private fun onClickBackButton() {
        binding.ivBack.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun initRecyclerViewLayout() {
        val oftenLayoutManager = FlexboxLayoutManager(context)
        val recentLayoutManager = FlexboxLayoutManager(context)
        val platformLayoutManager = FlexboxLayoutManager(context)

        recentLayoutManager.flexWrap = FlexWrap.WRAP
        oftenLayoutManager.flexWrap = FlexWrap.WRAP
        platformLayoutManager.flexWrap = FlexWrap.WRAP

        binding.rcvOften.layoutManager = oftenLayoutManager
        binding.rcvRecent.layoutManager = recentLayoutManager
        binding.rcvPlatform.layoutManager = platformLayoutManager
    }

    private fun setDataOnRecyclerView() {
        inputTagAdapter.submitList(searchTagViewModel.inputList)
        recentTagAdapter.submitList(searchTagViewModel.recentList)
        oftenTagAdapter.submitList(searchTagViewModel.oftenList)
        platformTagAdapter.submitList(searchTagViewModel.platformList)
    }

    private fun initAdapter() {
        inputTagAdapter = PointTagAdapter(::deleteTag)
        recentTagAdapter = PointSubTagAdapter(::selectTag)
        oftenTagAdapter = PointSubTagAdapter(::selectTag)
        platformTagAdapter = PointSubTagAdapter(::selectTag)

        binding.rcvInput.adapter = inputTagAdapter
        binding.rcvRecent.adapter = recentTagAdapter
        binding.rcvOften.adapter = oftenTagAdapter
        binding.rcvPlatform.adapter = platformTagAdapter
    }

    private fun checkInputNum() {
        if (searchTagViewModel.inputList.size > 6) {
            PhotoSurferSnackBar.make(requireView(), PhotoSurferSnackBar.CHOOSE_TAG_FRAGMENT).show()
        }
    }

    private fun deleteInput() {
        binding.ivClose.setOnClickListener {
            binding.etTag.text.clear()
        }
    }

    private fun convertTypingView() {
        binding.etTag.addTextChangedListener {
            binding.isTyping = binding.etTag.text.isEmpty()
        }
    }

    private fun setCompleteOnKeyBoardListener() {
        binding.etTag.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTagWithInputText(binding.etTag.text.toString())
            }
            false
        }
    }

    private fun addTagWithInputText(tag: String) {
        searchTagViewModel.inputList.add(TagInfo(0, tag))
        searchTagViewModel.setEmptyInput(searchTagViewModel.inputList.size)
        binding.etTag.text.clear()
    }

    private fun selectTag(tagInfo: TagInfo) {
        searchTagViewModel.selectTag(tagInfo)
        inputTagAdapter.submitList(searchTagViewModel.inputList)
        inputTagAdapter.notifyDataSetChanged()
    }

    private fun deleteTag(tagInfo: TagInfo) {
        searchTagViewModel.deleteTag(tagInfo)
        inputTagAdapter.submitList(searchTagViewModel.inputList)
    }
}