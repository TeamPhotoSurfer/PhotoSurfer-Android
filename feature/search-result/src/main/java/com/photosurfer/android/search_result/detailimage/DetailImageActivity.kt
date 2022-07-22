package com.photosurfer.android.search_result.detailimage

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.widget.PopupMenu
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.search_result.R
import com.photosurfer.android.search_result.SearchResultActivity.Companion.PHOTO_ID
import com.photosurfer.android.search_result.databinding.ActivityDetailImageBinding
import kotlin.properties.Delegates

class DetailImageActivity :
    BaseActivity<ActivityDetailImageBinding>(R.layout.activity_detail_image) {

    var photoId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onClickMenu()
        initExtraData()
//        startImageViewer()
    }

    private fun initExtraData(){
        photoId = intent.getIntExtra(PHOTO_ID,-1)
    }

//    private fun startImageViewer() {
//        binding.ivDetail.setOnClickListener {
//            StfalconImageViewerUtil.initImageViewer(
//                this,
//                binding.ivDetail,
//                listOf(imgUrl),
//                0
//            )
//        }
//    }

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
                    R.id.tag_add -> true
                    R.id.tag_edit -> true
                    R.id.tag_delete -> true
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    companion object {
        const val START = "START"
        const val ADD = "ADD"
        const val DELETE = "DELETE"
        const val ADJUST = "ADJUST"
    }
}
