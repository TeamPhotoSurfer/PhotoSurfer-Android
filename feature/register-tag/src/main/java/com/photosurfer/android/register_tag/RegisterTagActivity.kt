package com.photosurfer.android.register_tag

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.photosurfer.android.core.base.BaseActivity
import com.photosurfer.android.register_tag.databinding.ActivityRegisterTagBinding
import java.io.File

class RegisterTagActivity :
    BaseActivity<ActivityRegisterTagBinding>(R.layout.activity_register_tag) {

    private lateinit var chooseTagFragment: ChooseTagFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToChooseTagFragment()
        val uri: Uri? = getImgToUri()
        getRealPathFromURI(uri)
    }

    private fun getRealPathFromURI(contentUri: Uri?): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        cursor!!.moveToNext()
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
        val uri = Uri.fromFile(File(path))
        Log.d("uri to file : ", "$uri")
        cursor.close()
        return path
    }

    private fun getImgToUri(): Uri? {
        intent.getStringExtra(Intent.EXTRA_TEXT).toString()
        intent.getStringExtra(Intent.ACTION_SEND).toString()

        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val bundle = Bundle()
        val imageUri: Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)
        if (imageUri != null) {
            bundle.putString("image", imageUri.toString())
        } else {
            bundle.putString("image", "")
        }
        Log.d("uri 가져오", "${imageUri}")

        val file = File(imageUri.toString())
        Log.d("file path... ", "$file")
        return imageUri
    }

    private fun navigateToChooseTagFragment() {
        chooseTagFragment = ChooseTagFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, chooseTagFragment)
            .commit()
    }
}