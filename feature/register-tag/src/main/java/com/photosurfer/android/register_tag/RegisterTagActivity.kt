package com.photosurfer.android.register_tag

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.register_tag.databinding.ActivityRegisterTagBinding
import java.io.File

class RegisterTagActivity :
    BaseActivity<ActivityRegisterTagBinding>(R.layout.activity_register_tag) {

    private lateinit var chooseTagFragment: ChooseTagFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToChooseTagFragment()
        val file = getImageFile()
    }

    private fun getImageFile(): File {
        val bundle = Bundle()
        val imageUri: Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)

        intent.getStringExtra(Intent.EXTRA_TEXT).toString()
        intent.getStringExtra(Intent.ACTION_SEND).toString()
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        if (imageUri != null) {
            bundle.putString("image", imageUri.toString())
        } else {
            bundle.putString("image", "")
        }

        return File(imageUri.toString())
    }

    private fun navigateToChooseTagFragment() {
        chooseTagFragment = ChooseTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, chooseTagFragment)
            .commit()
    }
}