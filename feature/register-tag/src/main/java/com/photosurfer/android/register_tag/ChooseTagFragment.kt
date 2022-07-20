package com.photosurfer.android.register_tag

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding


class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {

    private val chooseTagViewModel: ChooseTagViewModel by viewModels()

    private lateinit var inputTagAdapter: PointTagAdapter
    private lateinit var recentTagAdapter: PointSubTagAdapter
    private lateinit var oftenTagAdapter: PointSubTagAdapter
    private lateinit var platformTagAdapter: PointSubTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true
        chooseTagViewModel.setTagList()

        initAdapter()
        setDataOnRecyclerView()
        observeInputChipGroup()
        convertTypingView()
        setCompleteOnKeyBoardListener()
        deleteInput()
        checkInputNum()
        initRecyclerViewLayout()
    }

    private fun initRecyclerViewLayout() {
        val oftenLayoutManager = FlexboxLayoutManager(context)
        oftenLayoutManager.flexWrap = FlexWrap.WRAP
        val recentLayoutManager = FlexboxLayoutManager(context)
        recentLayoutManager.flexWrap = FlexWrap.WRAP
        val platformLayoutManager = FlexboxLayoutManager(context)
        platformLayoutManager.flexWrap = FlexWrap.WRAP
        binding.rcvOften.layoutManager = oftenLayoutManager
        binding.rcvRecent.layoutManager = recentLayoutManager
        binding.rcvPlatform.layoutManager = platformLayoutManager
    }

    private fun setDataOnRecyclerView() {
        inputTagAdapter.submitList(chooseTagViewModel.inputList)
        recentTagAdapter.submitList(chooseTagViewModel.recentList)
        oftenTagAdapter.submitList(chooseTagViewModel.oftenList)
        platformTagAdapter.submitList(chooseTagViewModel.platformList)
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
        if (chooseTagViewModel.inputList.size > 6) {
            PhotoSurferSnackBar.make(requireView(), PhotoSurferSnackBar.CHOOSE_TAG_FRAGMENT).show()
        }
    }

    private fun deleteInput() {
        binding.ivClose.setOnClickListener {
            binding.etTag.text.clear()
        }
    }

    private fun observeInputChipGroup() {
        chooseTagViewModel.isEmptyInput.observe(viewLifecycleOwner) {
            if (chooseTagViewModel.isEmptyInput.value!! > 0) {
                binding.tvSave.isSelected = binding.ivCheckPlatform.isSelected != true
                binding.tvSave.isEnabled = true
            }
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
                addTagWithInputText()
            }
            false
        }
    }

    private fun addTagWithInputText() {
        chooseTagViewModel.inputList.add(TagInfo(0L, binding.etTag.text.toString()))
        chooseTagViewModel.setEmptyInput(chooseTagViewModel.inputList.size)
        binding.etTag.text.clear()
    }

    private fun selectTag(tagInfo: TagInfo) {
        chooseTagViewModel.selectTag(tagInfo)
        inputTagAdapter.submitList(chooseTagViewModel.inputList)
        inputTagAdapter.notifyDataSetChanged()
    }

    private fun deleteTag(tagInfo: TagInfo) {
        chooseTagViewModel.deleteTag(tagInfo)
        inputTagAdapter.submitList(chooseTagViewModel.inputList)
    }
}

enum class Platform(val platformName: String) {
    KAKAOTALK("카카오톡"),
    YOUTUBE("유튜브"),
    INSTAGRAM("인스타그램"),
    SHOPPINGMALL("쇼핑몰"),
    COMMUNITY("커뮤니티"),
    ETC("기타")
}