package com.photosurfer.android.register_tag

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.constant.TAG_LIST
import com.photosurfer.android.core.ext.getColor
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.SerializeTagInfoList
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.navigator.MainNavigator
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {

    private val chooseTagViewModel: ChooseTagViewModel by viewModels()

    private lateinit var inputTagAdapter: PointTagAdapter
    private lateinit var recentTagAdapter: PointSubTagAdapter
    private lateinit var oftenTagAdapter: PointSubTagAdapter
    private lateinit var platformTagAdapter: PointSubTagAdapter
    private lateinit var filterTagAdapter: PointSubTagAdapter
    private lateinit var tempList: ArrayList<TagInfo>

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.isTyping = true

        getRecommendTagList()
        setRecentList()
        setOftenList()
        setPlatformList()
        setAllItemList()
        chooseTagViewModel.getAllTagList()
        chooseTagViewModel.setEmptyInput()
        initAdapter()
        setDataOnRecyclerView()
        observeInputChipGroup()
        typingEditText()
        setCompleteOnKeyBoardListener()
        deleteInput()
        checkInputNum()
        initRecyclerViewLayout()
        initButtonSaveClickListener()
        initSaveTextColor()
        observeInputListNum()

    }

    private fun observeInputListNum() {
        chooseTagViewModel.inputListNum.observe(viewLifecycleOwner) {
            if (chooseTagViewModel.inputList.size > 0) {
                binding.tvSave.isEnabled = true
                binding.tvSave.setTextColor(getColor(com.photosurfer.android.shared.R.color.point_main))
            } else {
                binding.tvSave.isEnabled = false
                binding.tvSave.setTextColor(getColor(com.photosurfer.android.shared.R.color.gray_50))
            }
        }
    }

    private fun initSaveTextColor() {
        binding.tvSave.setTextColor(getColor(com.photosurfer.android.shared.R.color.gray_50))
    }

    private fun setAllItemList() {
        chooseTagViewModel.allItemList.observe(viewLifecycleOwner) {
            filterTagAdapter.submitList(chooseTagViewModel.allItemList.value)
        }
    }

    private fun getRecommendTagList() {
        chooseTagViewModel.getTagList()
    }

    private fun setPlatformList() {
        chooseTagViewModel.platformList.observe(viewLifecycleOwner) {
            platformTagAdapter.submitList(chooseTagViewModel.platformList.value)
            chooseTagViewModel.setPlatformIdList()
        }
    }

    private fun setOftenList() {
        chooseTagViewModel.oftenList.observe(viewLifecycleOwner) {
            oftenTagAdapter.submitList(chooseTagViewModel.oftenList.value)
        }
    }

    private fun setRecentList() {
        chooseTagViewModel.recentList.observe(viewLifecycleOwner) {
            recentTagAdapter.submitList(chooseTagViewModel.recentList.value)
        }
    }

    private fun initButtonSaveClickListener() {
        binding.tvSave.setOnClickListener {

            if (chooseTagViewModel.inputList.size > 0) {
                val bundle = Bundle().apply {
                    putInt("int", 0)
                }
                mainNavigator.navigatePushSettingFragment(requireActivity(), chooseTagViewModel.inputList)
            } else {
                Log.d("어쩔", "input 개수 0개")
            }
        }
    }

    private fun getImgToUri(): Uri? {
        val intent = activity?.intent
        intent?.getStringExtra(Intent.EXTRA_TEXT).toString()
        intent?.getStringExtra(Intent.ACTION_SEND).toString()

        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val bundle = Bundle()
        val imageUri: Uri? = intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        if (imageUri != null) {
            bundle.putString("image", imageUri.toString())
        } else {
            bundle.putString("image", "")
        }
        return imageUri
    }

    private fun setImgToFile(uri: Uri?): File {
        return File(getImgToUri().toString())
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
        recentTagAdapter.submitList(chooseTagViewModel.recentList.value)
        oftenTagAdapter.submitList(chooseTagViewModel.oftenList.value)
        platformTagAdapter.submitList(chooseTagViewModel.platformList.value)
        filterTagAdapter.submitList(chooseTagViewModel.allItemList.value)
    }

    private fun initAdapter() {
        inputTagAdapter = PointTagAdapter(::deleteTag)
        recentTagAdapter = PointSubTagAdapter(::selectTag)
        oftenTagAdapter = PointSubTagAdapter(::selectTag)
        platformTagAdapter = PointSubTagAdapter(::selectTag)
        filterTagAdapter = PointSubTagAdapter(::selectTag)

        binding.rcvInput.adapter = inputTagAdapter
        binding.rcvRecent.adapter = recentTagAdapter
        binding.rcvOften.adapter = oftenTagAdapter
        binding.rcvPlatform.adapter = platformTagAdapter
        binding.rvFilterList.adapter = filterTagAdapter
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
                binding.tvSave.isEnabled = true
            }
        }
    }

    private fun typingEditText() {
        binding.etTag.addTextChangedListener {
            setFilterList()
            converTypingView()
        }
    }

    private fun setFilterList() {
        filterTagAdapter.submitList(chooseTagViewModel.filterList(binding.etTag.text.toString()))
    }

    private fun converTypingView() {
        binding.isTyping = binding.etTag.text.isEmpty()
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
        chooseTagViewModel.inputList.add(TagInfo(0, binding.etTag.text.toString()))
        //chooseTagViewModel.setEmptyInput(chooseTagViewModel.inputList.size)
        binding.etTag.text.clear()
    }

    private fun selectTag(tagInfo: TagInfo) {
        chooseTagViewModel.selectTag(tagInfo)
        if(chooseTagViewModel.inputList.size < 7) {
            inputTagAdapter.submitList(chooseTagViewModel.inputList)
            chooseTagViewModel.inputListNum.value = chooseTagViewModel.inputList.size
            inputTagAdapter.notifyDataSetChanged()
        } else {
            PhotoSurferSnackBar.make(binding.clChooseTag, PhotoSurferSnackBar.CHOOSE_TAG_FRAGMENT)
                .show()
        }
    }

    private fun deleteTag(tagInfo: TagInfo) {
        chooseTagViewModel.deleteTag(tagInfo)
        inputTagAdapter.submitList(chooseTagViewModel.inputList)
    }
}

// TODO: POST 관련
//fun checkPlatform() {
//    var filteredList: MutableList<TagInfo> = mutableListOf()
//
//    for (i in 0 until 6) {
//        filteredList = chooseTagViewModel.inputList.value?.filter {
//            it.id == chooseTagViewModel.platformList.value?.get(i)?.id
//        } as MutableList<TagInfo>
//    }
//
//    Log.d("filtered List", "$filteredList")
//}
//
//private fun initCompleteButtonCLick() {
//    binding.tvSave.setOnClickListener {
//        uploadPost(
//            contents = chooseTagViewModel.inputList,
//            image = MultiPartResolver().createImgMultiPart(requireNotNull(imageUri), this)
//        )
//    }
//}

enum class Platform(val platformName: String) {
    KAKAOTALK("카카오톡"),
    YOUTUBE("유튜브"),
    INSTAGRAM("인스타그램"),
    SHOPPINGMALL("쇼핑몰"),
    COMMUNITY("커뮤니티"),
    ETC("기타")
}
