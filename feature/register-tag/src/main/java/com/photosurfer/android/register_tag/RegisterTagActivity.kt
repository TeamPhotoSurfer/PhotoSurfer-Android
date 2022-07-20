package com.photosurfer.android.register_tag

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.register_tag.databinding.ActivityRegisterTagBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


class RegisterTagActivity :
    BaseActivity<ActivityRegisterTagBinding>(R.layout.activity_register_tag) {

    private lateinit var chooseTagFragment: ChooseTagFragment
    private lateinit var chooseTagViewModel: ChooseTagViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        chooseTagViewModel = ChooseTagViewModel()
        navigateToChooseTagFragment()
    }

    private fun navigateToChooseTagFragment() {
        chooseTagFragment = ChooseTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, chooseTagFragment)
            .commit()
    }
}