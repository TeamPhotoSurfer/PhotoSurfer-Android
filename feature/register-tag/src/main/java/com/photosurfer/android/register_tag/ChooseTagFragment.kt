package com.photosurfer.android.register_tag

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding

class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {

    private val chooseTagViewModel: ChooseTagViewModel by viewModels()
    private lateinit var tagAdapter: PointTagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true

        val inputList: MutableList<TagInfo> = mutableListOf()

        chooseTagViewModel.setTagList()

        binding.rcvInput.adapter = PointTagAdapter(chooseTagViewModel.inputList)
        binding.rcvRecent.adapter = PointSubTagAdapter(chooseTagViewModel.recentList)
        binding.rcvOften.adapter = PointSubTagAdapter(chooseTagViewModel.oftenList)
        binding.rcvPlatform.adapter = PointSubTagAdapter(chooseTagViewModel.platformList)

        observeInputChipGroup()
        checkInputNum()
        addInputTag()
        deleteInput()

        binding.tvSave.setOnClickListener {
            Log.d("input list", "${chooseTagViewModel.inputList}")
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

    private fun checkInputNum() {
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
}