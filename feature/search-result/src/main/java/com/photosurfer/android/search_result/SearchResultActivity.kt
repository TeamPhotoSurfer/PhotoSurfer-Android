package com.photosurfer.android.search_result

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
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
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.domain.entity.SerializeTagInfoList
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.search_result.databinding.ActivitySearchResultBinding
import com.photosurfer.android.search_result.detailimage.DetailImageActivity
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

    }

    override fun onStart() {
        super.onStart()

        getExtraData()
        initExtraDataOnViewModel()
        initChipAdapter()
        getNewPhotoAsTagChanged()
        updatePhoto()
        setDefaultViewType()
        setCancelListener()
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
        // TODO observe 작동 안함 deep copy 사용방법으로 교체
        viewModel.tagList.observe(this) {
            Log.d(TAG, "getNewPhotoAsTagChanged: ${viewModel.tagList.value}")
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
        getPhotosByTags()
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
                val thumbnailId: Int =
                    viewModel.thumbnailList.value?.get(position)?.id
                        ?: throw IllegalStateException()
                val intent = Intent(this, DetailImageActivity::class.java).apply {
                    putExtra(PHOTO_ID, thumbnailId)
                }
                startActivity(intent)
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
                    // todo 데모데이 시연용 스낵바
                    R.id.tag_add, R.id.tag_edit, R.id.tag_delete -> {
                        PhotoSurferSnackBar.make(
                            binding.root,
                            PhotoSurferSnackBar.SERVICE_PREPARING
                        ).show()
                        true
                    }
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

    companion object {
        const val PHOTO_ID = "PHOTO_ID"
    }

}
