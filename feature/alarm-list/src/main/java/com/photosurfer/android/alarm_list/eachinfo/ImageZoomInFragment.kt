package com.photosurfer.android.alarm_list.eachinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.CLICKABLE_STATE
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.PUSH_ID
import com.photosurfer.android.alarm_list.R
import com.photosurfer.android.alarm_list.databinding.FragmentImageZoomInBinding
import com.photosurfer.android.alarm_list.eachinfo.EachInfoActivity.Companion.PUSH_MAIN_STATE
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.core.constant.PushSettingConstant
import com.photosurfer.android.core.constant.PushSettingConstant.PUSH_MAIN
import com.photosurfer.android.core.util.PhotoSurferSnackBar.Companion.PUSH_MAIN_FRAGMENT
import com.photosurfer.android.core.util.StfalconImageViewerUtil
import com.photosurfer.android.navigator.MainNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageZoomInFragment :
    BaseFragment<FragmentImageZoomInBinding>(R.layout.fragment_image_zoom_in) {

    @Inject
    lateinit var mainNavigator: MainNavigator
    private val eachInfoViewModel by activityViewModels<EachInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eachInfoViewModel = eachInfoViewModel
        startImageViewer()
        initCheckAlarmButton()
    }

    private fun initCheckAlarmButton() {
        binding.btnCheckAlarm.setOnClickListener {
            val bundle = Bundle().apply {
                putLong(PUSH_ID, eachInfoViewModel.pushId.value ?: throw IllegalStateException())
                putBoolean(CLICKABLE_STATE, false)
            }
            mainNavigator.transactionPushMainFragment(requireActivity(), bundle)
            eachInfoViewModel.updateFragmentState(PUSH_MAIN_STATE)
        }
    }

    private fun startImageViewer() {
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
