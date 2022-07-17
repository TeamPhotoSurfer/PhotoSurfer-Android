package com.photosurfer.android.search

import android.os.Bundle
import com.example.register_tag.ChooseTagFragment
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.search.databinding.ActivitySearchTagBinding

class SearchTagActivity : BaseActivity<ActivitySearchTagBinding>(R.layout.activity_search_tag) {
    // private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chooseTagFragment = ChooseTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_select_tag, chooseTagFragment)
            .commit()
    }
}