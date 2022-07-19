package com.photosurfer.android.search_result

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.TagResultViewType
import com.photosurfer.android.core.ext.shortToast
import com.photosurfer.android.core.util.ChipCancelableUtil
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.core.util.ItemDividerGrid
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.search_result.databinding.ActivitySearchResultBinding
import com.photosurfer.android.search_result.viewModel.SearchResultViewModel

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var thumbnailAdapter: ThumbnailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setChip()
        setDefaultViewType()
        setCancelListener()
        initAdapter()
        initThumbnailList()
        setItemDecoration()
        initLearnAddTag()
        setBackButtonClickListener()
        setSelectClickListener()
    }

    private fun setDefaultViewType() {
        binding.currentViewType = TagResultViewType.DEFAULT
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setSelectClickListener() {
        binding.tvChoose.setOnClickListener {
            binding.currentViewType = TagResultViewType.SELECT
            copyChipList()
        }
    }

    private fun copyChipList() {
        binding.cgSearchTagSelect.removeAllViews()
        val tagList = binding.cgSearchTagDefault.checkedChipIds
        Log.d(TAG, "copyChipList: $tagList")
        for (i in 0 until tagList.size) {
            val currentTagIdx = tagList[i] - 1
            Log.d(TAG, "copyChipList: $currentTagIdx")
            val name = viewModel.originTagList.value?.get(currentTagIdx)?.name
            ChipUnSelectableUtil(this).make(binding.cgSearchTagSelect, name!!) {}
        }
        // binding.cgSearchTagDefault.size
    }

    private fun setCancelListener() {
        binding.tvCancel.setOnClickListener {
            // viewModel 에서 selectedList -> emptyList()로 해주기
            binding.currentViewType = TagResultViewType.DEFAULT
        }
    }

    private fun initLearnAddTag() {
        binding.layoutSearchResultEmpty.btnLearnAddTag.setOnClickListener {
            // TODO : 온보딩 dialog 띄워줌
        }
    }

    private fun initAdapter() {
        binding.rvThumbnail.adapter =
            ThumbnailAdapter(::onItemClick).also { thumbnailAdapter = it }
    }

    private fun initThumbnailList() {
        viewModel.thumbnail.observe(this) { list ->
            if (list != null) thumbnailAdapter.submitList(list)
        }
    }

    private fun setItemDecoration() {
        binding.rvThumbnail.addItemDecoration(
            ItemDividerGrid(3, 3F, 3F)
        )
    }

    private fun onItemClick(thumbnail: ThumbnailInfo) {
        this.shortToast(thumbnail.id.toString())
    }

    // Selectable Chip 만들면 코드 교체
    private fun setChip() {
        val tagList = viewModel.originTagList.value ?: return
        for (element in tagList) {
            ChipCancelableUtil(this).make(
                binding.cgSearchTagDefault,
                element.name,
                ::onClickChip
            )
        }
    }

    private fun onClickMenu() {
        // Menu PopUp 띄우기
    }

    private fun changeItem() {
        // select 뷰타입에 따라 check 박스 처리하기
    }

    private fun onClickChip() {
        viewModel.updateList(binding.cgSearchTagDefault.checkedChipIds)
    }
}