package com.photosurfer.android.register_tag

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {
    private val chooseTagViewModel: ChooseTagViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true
        setChips()
        checkInputNum()
        checkPlatform()
        addInputTag()
        observeInputChipGroup()
    }

    private fun observeInputChipGroup() {
        chooseTagViewModel.isEmptyInput.observe(viewLifecycleOwner) {
            if (chooseTagViewModel.isEmptyInput.value!! > 0) {
                binding.tvSave.isSelected = binding.ivCheckPlatform.isSelected != true
                binding.tvSave.isEnabled = true
            }
        }
    }

    private fun addInputTag() {
        binding.etTag.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                ChipUnSelectableUtil(requireContext()).make(
                    binding.cgInput,
                    binding.etTag.text.toString()
                )
                chooseTagViewModel.setEmptyInput(binding.cgInput.childCount)
                binding.etTag.setText("")
                true
            }
            false
        }
    }

    private fun checkPlatform() {
        binding.clCheckPlatform.setOnClickListener {
            binding.ivCheckPlatform.isSelected = binding.ivCheckPlatform.isSelected != true
            binding.tvCheckPlatform.isSelected = binding.tvCheckPlatform.isSelected != true
        }
    }

    private fun checkInputNum() {
        binding.etTag.addTextChangedListener {
            binding.isTyping = binding.etTag.text.isEmpty()
        }
    }

    private fun setChips() {
        for (element in Category.values()) {
            ChipUnSelectableUtil(requireContext()).make(
                binding.cgRecent,
                element.name
            )
        }

        for (element in Category.values()) {
            ChipUnSelectableUtil(requireContext()).make(
                binding.cgOften,
                element.name
            )
        }

        for (element in Category.values()) {
            ChipUnSelectableUtil(requireContext()).make(
                binding.cgPlatform,
                element.name
            )
        }
    }

    enum class Category(val categoryName: String) {
        PHOTOSURFER("포토서퍼"),
        CAFE("카페"),
        LIVETIP("생활꿀팁"),
        WISHLIST("위시리스트"),
        PLAN("휴학계획"),
        TRAVEL("여행");
    }

    enum class TagDataExample(val tag: String) {
        T("t"),
        TE("te"),
        TES("tes"),
        TEST("test");
    }
}