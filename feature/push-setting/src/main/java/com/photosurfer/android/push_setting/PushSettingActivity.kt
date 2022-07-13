package com.photosurfer.android.push_setting

import android.os.Bundle
import androidx.fragment.app.replace
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.push_setting.databinding.ActivityPushSettingBinding
import com.photosurfer.android.push_setting.fragments.PushMainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PushSettingActivity :
    BaseActivity<ActivityPushSettingBinding>(R.layout.activity_push_setting) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initStartFragment()
    }

    private fun initStartFragment() {
        supportFragmentManager.beginTransaction()
            .replace<PushMainFragment>(R.id.container_push_setting).commit()
    }
}
