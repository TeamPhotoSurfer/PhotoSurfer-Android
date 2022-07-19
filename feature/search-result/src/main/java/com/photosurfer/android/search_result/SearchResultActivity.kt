package com.photosurfer.android.search_result

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.ext.shortToast
import com.photosurfer.android.core.util.ChipCancelableUtil
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
        initAdapter()
        initThumbnailList()
        setItemDecoration()
        initLearnAddTag()
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
                binding.cgSearchTag,
                element.name,
                ::onClickChip
            )
        }
    }

    private fun onClickChip() {
        viewModel.updateList(binding.cgSearchTag.checkedChipIds)
    }
}
