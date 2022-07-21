package com.photosurfer.android.search

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.search.databinding.ActivitySearchTagBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTagActivity : BaseActivity<ActivitySearchTagBinding>(R.layout.activity_search_tag) {
    // private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentOnFCV()
    }

    private fun setFragmentOnFCV() {
        val searchTagFragment = SearchTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_select_tag, searchTagFragment)
            .commit()
    }
}