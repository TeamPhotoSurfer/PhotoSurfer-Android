package com.photosurfer.android.core.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.photosurfer.android.shared.R
import com.photosurfer.android.shared.databinding.DialogOnboardingBinding

class AddTagOnBoardingFragmentDialog : DialogFragment() {

    private lateinit var binding: DialogOnboardingBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initIndicator()
        initSkipButton()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            OnBoardingTagfirstFragment(),
            OnBoardingTagSecondFragment(),
            OnBoardingTagThirdFragment()
        )

        onBoardingAdapter = OnBoardingAdapter(this)
        onBoardingAdapter.fragments.addAll(fragmentList)

        binding.vpOnBoarding.adapter = onBoardingAdapter
    }

    private fun initIndicator() {
        binding.indicatorOnBoarding.attachTo(binding.vpOnBoarding)
    }

    private fun initSkipButton() {
        binding.tvSkip.setOnClickListener {
            this.dismiss()
        }
    }
}
