package com.photosurfer.android.search_result

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.core.util.ChipUnSelectableUtil
import com.photosurfer.android.search_result.databinding.ActivitySearchResultBinding
import com.photosurfer.android.search_result.viewModel.SearchResultViewModel
import timber.log.Timber

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel: SearchResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setChip()
    }

    // Selectable Chip 만들면 코드 교체
    private fun setChip() {
        val tagList = viewModel.fakeOftenTagList
        for (element in tagList) {
            ChipUnSelectableUtil(this).make(
                binding.cgSearchTag,
                element.name,
                ::onClickChip
            )
        }
    }

    private fun onClickChip() {
        Timber.d("clciked chip")
    }
}