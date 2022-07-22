package com.photosurfer.android.search_result.detailimage

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.content.ContentProviderCompat.requireContext
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.search_result.R
import com.photosurfer.android.search_result.databinding.ActivityDetailImageBinding

class DetailImageActivity :
    BaseActivity<ActivityDetailImageBinding>(R.layout.activity_detail_image) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onClickMenu()
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
        const val ADD = "ADD"
        const val DELETE = "DELETE"
        const val ADJUST = "ADJUST"
    }
}
