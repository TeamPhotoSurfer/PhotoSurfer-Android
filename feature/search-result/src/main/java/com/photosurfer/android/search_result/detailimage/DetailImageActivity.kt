package com.photosurfer.android.search_result.detailimage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.PopupMenu
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.constant.DialogMode
import com.photosurfer.android.core.util.*
import com.photosurfer.android.search_result.R
import com.photosurfer.android.search_result.SearchResultActivity.Companion.PHOTO_ID
import com.photosurfer.android.search_result.databinding.ActivityDetailImageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class DetailImageActivity :
    BaseActivity<ActivityDetailImageBinding>(R.layout.activity_detail_image) {

    private val detailImageViewModel by viewModels<DetailImageViewModel>()
    var photoId by Delegates.notNull<Int>()
    lateinit var dialog: PhotoSurferDialogUtil
    lateinit var detailImageAdapter: DetailImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onClickMenu()
        initExtraData()
        initViewModelData()
        getDetailImageInfo()
        binding.detailImageViewModel = detailImageViewModel
        initAdapter()
        initAdapterDataObserver()
        startImageViewer()
        initShareButtonClickListener()
        deleteImageSuccess()
        initDeleteButtonClickListener()
    }

    private fun initDeleteButtonClickListener() {
        dialog = PhotoSurferDialogUtil(DialogMode.DELETE_ONE_PHOTO.name, ::deleteImage)
        binding.ivDelete.setOnClickListener {
            dialog.show(supportFragmentManager, this.javaClass.name)
        }
    }

    private fun deleteImage() {
        detailImageViewModel.deleteImage()
    }

    private fun initExtraData() {
        photoId = intent.getIntExtra(PHOTO_ID, -1)
    }

    private fun initViewModelData() {
        detailImageViewModel.updatePhotoId(photoId)
    }

    private fun getDetailImageInfo() {
        detailImageViewModel.getDetailImageInfo()
    }

    private fun startImageViewer() {
        binding.ivDetail.setOnClickListener {
            StfalconImageViewerUtil.initImageViewer(
                this,
                binding.ivDetail,
                listOf(detailImageViewModel.imageUrl.value ?: ""),
                0
            )
        }
    }

    private fun onClickMenu() {
        binding.btnMore.setOnClickListener {
            val wrapper: Context = androidx.appcompat.view.ContextThemeWrapper(
                this,
                com.photosurfer.android.shared.R.style.popupMenuStyle
            )

            val popupMenu = PopupMenu(wrapper, binding.btnMore, Gravity.RIGHT)
            popupMenu.inflate(R.menu.menu_search_result)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.tag_add -> {
                        binding.updateState = true
                        detailImageViewModel.updateProcessState(ADD)
                        true
                    }
                    R.id.tag_edit -> {
                        binding.updateState = true
                        detailImageViewModel.updateProcessState(EDIT)
                        true
                    }
                    R.id.tag_delete -> {
                        binding.updateState = true
                        detailImageViewModel.updateProcessState(DELETE)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    private fun initShareButtonClickListener() {
        binding.ivShare.setOnClickListener {
            useBitmapImg(this, detailImageViewModel.imageUrl.value ?: "") {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "image/*"
                    putExtra(
                        Intent.EXTRA_STREAM,
                        getImageUriFromBitmap(this@DetailImageActivity, it)
                    )
                }
                startActivity(Intent.createChooser(intent, "공유하기"))
            }
        }
    }

    private fun deleteImageSuccess() {
        detailImageViewModel.deleteSuccessState.observe(
            this,
            EventObserver {
                finish()
            }
        )
    }

    private fun initAdapter(){
        detailImageAdapter = DetailImageAdapter()
        binding.rvTagDetail.adapter = detailImageAdapter
    }

    private fun initAdapterDataObserver(){
        detailImageViewModel.tagInfoList.observe(this){
            detailImageAdapter.submitList(it)
        }
    }

    companion object {
        const val START = "START"
        const val ADD = "ADD"
        const val DELETE = "DELETE"
        const val EDIT = "EDIT"
    }
}
