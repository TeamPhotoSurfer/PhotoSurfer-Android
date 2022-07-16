package com.photosurfer.android.search

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.search.databinding.ActivitySearchTagBinding

class SearchTagActivity : BaseActivity<ActivitySearchTagBinding>(R.layout.activity_search_tag) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tag)
    }
}