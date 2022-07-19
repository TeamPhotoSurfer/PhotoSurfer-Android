package com.photosurfer.android.register_tag

import android.os.Bundle
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.register_tag.databinding.ActivityRegisterTagBinding

class RegisterTagActivity :
    BaseActivity<ActivityRegisterTagBinding>(R.layout.activity_register_tag) {

    private lateinit var chooseTagFragment: ChooseTagFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chooseTagFragment = ChooseTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, chooseTagFragment)
            .commit()
    }
}