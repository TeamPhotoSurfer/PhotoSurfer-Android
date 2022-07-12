package com.photosurfer.android

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
