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
import com.photosurfer.android.core.util.KeyBoardUtil
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.register_tag.PointSubTagAdapter
import com.photosurfer.android.register_tag.PointTagAdapter
import com.photosurfer.android.search.databinding.FragmentSearchTagBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SearchTagFragment : BaseFragment<FragmentSearchTagBinding>(R.layout.fragment_search_tag) {

    private val viewModel: SearchTagViewModel by viewModels()

    private lateinit var inputTagAdapter: PointTagAdapter
    private lateinit var recentTagAdapter: PointSubTagAdapter
    private lateinit var oftenTagAdapter: PointSubTagAdapter
    private lateinit var platformTagAdapter: PointSubTagAdapter

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true

        setKeyBoardFocus()
        getRecommendTagList()
        setRecentList()
        setOftenList()
        setPlatformList()
        getExtraData()
        onClickBackButton()
        initAdapter()
        setDataOnRecyclerView()
        convertTypingView()
        setCompleteOnKeyBoardListener()
        deleteInput()
        initRecyclerViewLayout()
    }

    private fun setKeyBoardFocus() {
        KeyBoardUtil.show(requireActivity(), binding.etTag)
    }

    private fun setPlatformList() {
        viewModel.platformList.observe(viewLifecycleOwner) {
            platformTagAdapter.submitList(viewModel.platformList.value)
        }
    }

    private fun setOftenList() {
        viewModel.oftenList.observe(viewLifecycleOwner) {
            oftenTagAdapter.submitList(viewModel.oftenList.value)
        }
    }

    private fun setRecentList() {
        viewModel.recentList.observe(viewLifecycleOwner) {
            recentTagAdapter.submitList(viewModel.recentList.value)
        }
    }


    private fun getRecommendTagList() {
        viewModel.getTagList()
    }

    private fun getExtraData() {
        val tagInfo = requireActivity().intent.getSerializableExtra(SELECTED_TAG) as? TagInfo
        Timber.d("tagInfo from previous $tagInfo")
        tagInfo?.let { addTagWithInputText(it) }
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
        inputTagAdapter.submitList(viewModel.inputList)
        recentTagAdapter.submitList(viewModel.recentList.value)
        oftenTagAdapter.submitList(viewModel.oftenList.value)
        platformTagAdapter.submitList(viewModel.platformList.value)
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
        if (viewModel.inputList.size > 6) {
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

    private fun isTypingNow() = binding.etTag.text.toString() != ""

    private fun setCompleteOnKeyBoardListener() {
        binding.etTag.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isTypingNow()) {
                    // 검색 창 뜨게
                } else if (isTagSelectedAtListOnce()) {
                    navigateToSearchTagActivity()
                    requireActivity().finish()
                }
            }
            false
        }
    }

    private fun isTagSelectedAtListOnce(): Boolean = viewModel.inputList.size != 0

    private fun navigateToSearchTagActivity() {
        mainNavigator.navigateSearchResult(requireContext(), viewModel.inputList.toList())
    }

    private fun addTagWithInputText(tag: TagInfo) {
        viewModel.inputList.add(TagInfo(tag.id, tag.name))
        viewModel.setEmptyInput(viewModel.inputList.size)
        binding.etTag.text.clear()
    }

    private fun selectTag(tagInfo: TagInfo) {
        viewModel.selectTag(tagInfo)
        inputTagAdapter.submitList(viewModel.inputList)
        inputTagAdapter.notifyDataSetChanged()
    }

    private fun deleteTag(tagInfo: TagInfo) {
        viewModel.deleteTag(tagInfo)
        inputTagAdapter.submitList(viewModel.inputList)
    }

}