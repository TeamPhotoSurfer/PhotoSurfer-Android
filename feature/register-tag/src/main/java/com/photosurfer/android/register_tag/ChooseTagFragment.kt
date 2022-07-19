package com.photosurfer.android.register_tag

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
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

        inputTagAdapter = PointTagAdapter(::deleteTag)
        recentTagAdapter = PointSubTagAdapter(::selectTag)
        oftenTagAdapter = PointSubTagAdapter(::selectTag)
        platformTagAdapter = PointSubTagAdapter(::selectTag)

        inputTagAdapter.submitList(chooseTagViewModel.inputList)
        recentTagAdapter.submitList(chooseTagViewModel.recentList)
        oftenTagAdapter.submitList(chooseTagViewModel.oftenList)
        platformTagAdapter.submitList(chooseTagViewModel.platformList)

        binding.rcvInput.adapter = inputTagAdapter
        binding.rcvRecent.adapter = recentTagAdapter
        binding.rcvOften.adapter = oftenTagAdapter
        binding.rcvPlatform.adapter = platformTagAdapter

        observeInputChipGroup()
        convertTypingView()
        addInputTag()
        deleteInput()
        checkInputNum()
    }

    private fun checkInputNum() {
        if(chooseTagViewModel.inputList.size > 6) {
            // 토스트 메시지 띄우기
            Toast.makeText(requireContext(),"태그는 최대 6개까지만 추가할 수 있어요.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteInput() {
        binding.ivClose.setOnClickListener {
            binding.etTag.setText("")
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

    private fun addInputTag() {
        binding.etTag.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                chooseTagViewModel.inputList.add(TagInfo(0L, binding.etTag.text.toString()))
                chooseTagViewModel.setEmptyInput(chooseTagViewModel.inputList.size)
                binding.etTag.setText("")
                true
            }
            false
        }
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

    enum class Platform(val platformName: String) {
        KAKAOTALK("카카오톡"),
        YOUTUBE("유튜브"),
        INSTAGRAM("인스타그램"),
        SHOPPINGMALL("쇼핑몰"),
        COMMUNITY("커뮤니티"),
        ETC("기타")
    }
}