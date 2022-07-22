package com.photosurfer.android.search_result

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.appcompat.view.ContextThemeWrapper
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.TAG_LIST
import com.photosurfer.android.core.constant.TagResultViewType
import com.photosurfer.android.core.onboarding.AddTagOnBoardingFragmentDialog
import com.photosurfer.android.core.util.ItemDividerGrid
import com.photosurfer.android.domain.entity.SerializeTagInfoList
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.search_result.databinding.ActivitySearchResultBinding
import com.photosurfer.android.search_result.viewModel.SearchResultViewModel
import com.photosurfer.android.shared.R.style
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var thumbnailAdapter: ThumbnailAdapter
    private lateinit var chipAdapter: MutableTagAdapter
    private lateinit var extraTag: List<TagInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getExtraData()
        initExtraDataOnViewModel()
        getNewPhotoAsTagChanged()
        updatePhoto()
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
        onClickMenu()
    }

    private fun updatePhoto() {
        viewModel.thumbnailList.observe(this) {
            thumbnailAdapter.submitList(viewModel.thumbnailList.value)
        }
    }

    private fun getNewPhotoAsTagChanged() {
        viewModel.tagList.observe(this) {
            getPhotosByTags()
        }
    }

    private fun getPhotosByTags() {
        viewModel.getPhotosByTags()
    }

    private fun getExtraData() {
        extraTag = (intent.getSerializableExtra(TAG_LIST) as SerializeTagInfoList).TagInfoList
        for (i in extraTag.indices) {
            viewModel.tagList.value?.add(extraTag[i])
        }
    }

    private fun initExtraDataOnViewModel() {
        viewModel.setOriginTagList(extraTag)
        viewModel.setTempTagList(extraTag)
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
            doOnCancelClicked()
        }
    }

    private fun doOnCancelClicked() {
        viewModel.clearCheckedThumbnail()
        setViewTypeAsDefault()
        updateChipAdapter()
        updateThumbnailAdapterWithViewModel()
        chipAdapter.toggleCancelable()
        viewModel.clearCheckedTempThumbnail()
    }

    private fun setViewTypeAsDefault() {
        binding.currentViewType = TagResultViewType.DEFAULT
    }

    private fun updateChipAdapter() {
        chipAdapter.notifyItemRangeChanged(0, viewModel.originTagList.value?.size ?: return)
    }

    private fun updateThumbnailAdapterWithViewModel() {
        val selectedThumbnailList = viewModel.selectedThumbnailListPosition
        Log.d(TAG, "updateThumbnailAdapterWithViewModel: ${selectedThumbnailList.size}")
        for (i in 0 until selectedThumbnailList.size) {
            thumbnailAdapter.notifyItemChanged(selectedThumbnailList[i])
            Log.d(TAG, "updateThumbnailAdapterWithViewModel: ${selectedThumbnailList[i]}")
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

    private fun onItemClick(thumbnail: ThumbnailInfo, position: Int) {
        when (binding.currentViewType ?: TagResultViewType.DEFAULT) {
            TagResultViewType.DEFAULT -> {
                // TODO 창환~~ 미정이뷰 Navigate 로직 넣어조
                val thumbnailId: Int =
                    viewModel.thumbnailList.value?.get(position)?.id
                        ?: throw IllegalStateException()
                Log.d(TAG, "onItemClick: $thumbnailId")
            }
            TagResultViewType.SELECT -> {
                thumbnail.isChecked = !thumbnail.isChecked
                viewModel.updateSelectedThumbnailList(thumbnail, position)
                thumbnailAdapter.notifyItemChanged(position)
            }
        }
    }

    private fun initThumbnailList() {
        viewModel.thumbnailList.observe(this) { list ->
            if (list != null) thumbnailAdapter.submitList(list)
        }
    }

    private fun setItemDecoration() {
        binding.rvThumbnail.addItemDecoration(
            ItemDividerGrid(3, 3F, 3F)
        )
    }

    private fun onClickMenu() {
        binding.ivMenu.setOnClickListener {
            val wrapper: Context = ContextThemeWrapper(this, style.popupMenuStyle)
            val popupMenu = PopupMenu(wrapper, binding.ivMenu, Gravity.RIGHT)
            popupMenu.inflate(R.menu.menu_search_result)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.tag_add -> true
                    R.id.tag_edit -> true
                    R.id.tag_delete -> true
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    override fun onBackPressed() {
        val viewType = binding.currentViewType
        if (viewType == TagResultViewType.DEFAULT)
            super.onBackPressed()
        else doOnCancelClicked()
    }
}