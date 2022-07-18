package com.photosurfer.android.alarm_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.photosurfer.android.alarm_list.databinding.FragmentImageZoomInBinding
import com.photosurfer.android.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageZoomInFragment :
    BaseFragment<FragmentImageZoomInBinding>(R.layout.fragment_image_zoom_in) {

    private val alarmListExtraViewModel by activityViewModels<AlarmListExtraViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBindingData()
    }

    private fun initBindingData() {
        binding.alarmListExtraViewModel = alarmListExtraViewModel
    }
}
