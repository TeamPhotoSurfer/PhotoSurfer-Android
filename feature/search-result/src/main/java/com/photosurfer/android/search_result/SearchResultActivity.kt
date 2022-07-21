package com.photosurfer.android.search_result

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.TAG_LIST
import com.photosurfer.android.core.constant.TagResultViewType
import com.photosurfer.android.core.ext.shortToast
import com.photosurfer.android.core.onboarding.AddTagOnBoardingFragmentDialog
import com.photosurfer.android.core.util.ItemDividerGrid
import com.photosurfer.android.domain.entity.SerializeTagInfoList
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.search_result.databinding.ActivitySearchResultBinding
import com.photosurfer.android.search_result.viewModel.SearchResultViewModel

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var thumbnailAdapter: ThumbnailAdapter
    private lateinit var chipAdapter: MutableTagAdapter
    private lateinit var inputTag: List<TagInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getExtraData()
        initExtraDataOnViewModel()
        setDefaultViewType()
        setCancelListener()
        initChipAdapter()
        setDataOnRecyclerView()
        initThumbnailAdapter()
        initThumbnailList()
        setItemDecoration()
        initLearnAddTag()
        setBackButtonClickListener()
        setSelectClickListener()
    }

    private fun getExtraData() {
        inputTag = (intent.getSerializableExtra(TAG_LIST) as SerializeTagInfoList).TagInfoList
    }

    private fun initExtraDataOnViewModel() {
        viewModel.setOriginTagList(inputTag)
        viewModel.setTempTagList(inputTag)
    }

    private fun setDefaultViewType() {
        binding.currentViewType = TagResultViewType.DEFAULT
    }

    private fun initChipAdapter() {
        chipAdapter = MutableTagAdapter(::deleteTag)
        binding.rcvTag.adapter = chipAdapter
    }

    private fun deleteTag(position: Int) {
        viewModel.deleteTag(position)
        chipAdapter.notifyItemRemoved(position)
    }

    private fun setDataOnRecyclerView() {
        chipAdapter.submitList(viewModel.tagList.value)
    }

    private fun setBackButtonClickListener() {
        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setSelectClickListener() {
        binding.tvChoose.setOnClickListener {
            binding.currentViewType = TagResultViewType.SELECT
            chipAdapter.toggleCancelable()
            chipAdapter.notifyItemRangeChanged(
                0,
                viewModel.originTagList.value?.size ?: return@setOnClickListener
            )
        }
    }

    private fun setCancelListener() {
        binding.tvCancel.setOnClickListener {
            // viewModel 에서 selectedList -> emptyList()로 해주기
            binding.currentViewType = TagResultViewType.DEFAULT
            chipAdapter.toggleCancelable()
            chipAdapter.notifyItemRangeChanged(
                0,
                viewModel.originTagList.value?.size ?: return@setOnClickListener
            )
        }
    }

    private fun initLearnAddTag() {
        binding.layoutSearchResultEmpty.btnLearnAddTag.setOnClickListener {
            AddTagOnBoardingFragmentDialog().show(supportFragmentManager, "Tag")
        }
    }

    private fun initThumbnailAdapter() {
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

    private fun onClickMenu() {
        // Menu PopUp 띄우기
    }

    private fun changeItem() {
        // select 뷰타입에 따라 check 박스 처리하기
    }
}