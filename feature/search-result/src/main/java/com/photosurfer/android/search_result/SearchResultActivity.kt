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
        onClickMenu()
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

    private fun onItemClick(thumbnail: ThumbnailInfo, position: Int) {
        when (binding.currentViewType ?: TagResultViewType.DEFAULT) {
            TagResultViewType.DEFAULT -> {
                // TODO 창환~~ 미정이뷰 Navigate 로직 넣어조
                val thumbnailId: Int =
                    viewModel.thumbnail.value?.get(position)?.id ?: throw IllegalStateException()
                Log.d(TAG, "onItemClick: $thumbnailId")
            }
            TagResultViewType.SELECT -> {
                thumbnail.isChecked = !thumbnail.isChecked
                thumbnailAdapter.notifyItemChanged(position)
            }
        }
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

    private fun onClickMenu() {
        binding.ivMenu.setOnClickListener {
            val wrapper: Context = ContextThemeWrapper(
                this,
                com.photosurfer.android.shared.R.style.popupMenuStyle
            )

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
}