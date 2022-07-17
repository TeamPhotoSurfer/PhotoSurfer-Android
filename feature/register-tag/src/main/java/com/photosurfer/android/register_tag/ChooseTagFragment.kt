package com.photosurfer.android.register_tag

import android.nfc.Tag
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {
    private val chooseTagViewModel: ChooseTagViewModel by viewModels()

    private lateinit var tagAdapter: TagAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true
        setChips()
        checkInputNum()
        checkPlatform()
        addInputTag()
        observeInputChipGroup()

        tagAdapter = TagAdapter()
        binding.rcvMultiview.adapter = tagAdapter

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
    }
/*
    inner class ItemFilter: Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<TagInfo> = ArrayList<TagInfo>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = persons
                results.count = persons.size

                return results
                //공백제외 2글자 이인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (person in persons) {
                    if (person.name.contains(filterString)) {
                        filteredList.add(person)
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            } else {
                for (person in persons) {
                    if (person.name.contains(filterString) || person.phoneNumber.contains(
                            filterString
                        )
                    ) {
                        filteredList.add(person)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            TODO("Not yet implemented")
        }
    }

 */
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