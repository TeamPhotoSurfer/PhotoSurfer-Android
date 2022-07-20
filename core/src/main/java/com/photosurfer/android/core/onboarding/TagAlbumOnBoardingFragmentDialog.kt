package com.photosurfer.android.core.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.photosurfer.android.shared.R
import com.photosurfer.android.shared.databinding.DialogOnboardingBinding

class TagAlbumOnBoardingFragmentDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }

    private lateinit var binding: DialogOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }
}
