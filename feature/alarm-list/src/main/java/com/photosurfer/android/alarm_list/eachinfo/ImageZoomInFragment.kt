package com.photosurfer.android.alarm_list.eachinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.alarm_list.R
import com.photosurfer.android.alarm_list.databinding.FragmentImageZoomInBinding
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.util.StfalconImageViewerUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageZoomInFragment :
    BaseFragment<FragmentImageZoomInBinding>(R.layout.fragment_image_zoom_in) {

    private val eachInfoViewModel by activityViewModels<EachInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eachInfoViewModel = eachInfoViewModel
        startImageViewer()
    }

    fun initCheckAlarmButton() {
        binding.btnCheckAlarm.setOnClickListener {
        }
    }

    fun startImageViewer() {
        binding.ivPicture.setOnClickListener {
            StfalconImageViewerUtil.initImageViewer(
                requireActivity(),
                binding.ivPicture,
                listOf(eachInfoViewModel.imgurl.value ?: throw IllegalStateException()),
                0
            )
        }
    }
}
