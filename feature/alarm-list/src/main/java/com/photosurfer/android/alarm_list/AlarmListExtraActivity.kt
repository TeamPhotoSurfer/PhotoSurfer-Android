package com.photosurfer.android.alarm_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.replace
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.PASSED_ALARM
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.START_POINT
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.UP_COMING_ALARM
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.ZOOM_IN_IMAGE
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.ZOOM_IN_IMAGE_URL
import com.photosurfer.android.alarm_list.databinding.ActivityAlarmListMoreBinding
import com.photosurfer.android.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class AlarmListExtraActivity :
    BaseActivity<ActivityAlarmListMoreBinding>(R.layout.activity_alarm_list_more) {

    private val alarmListExtraViewModel by viewModels<AlarmListExtraViewModel>()
    private var startPoint by Delegates.notNull<Int>()
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtraData()
        initBindingData()
        initFragmentState()
        initStartFragmentData()
        initStartFragmentAsStartPoint()
    }

    private fun initExtraData() {
        startPoint = intent.getIntExtra(START_POINT, 1)
        imageUrl = intent.getStringExtra(ZOOM_IN_IMAGE_URL)
    }

    private fun initBindingData() {
        binding.alarmListExtraViewModel = alarmListExtraViewModel
    }

    private fun initFragmentState(){
        alarmListExtraViewModel.updateFragmentState(startPoint)
    }

    private fun initStartFragmentAsStartPoint() {
        if (startPoint == PASSED_ALARM || startPoint == UP_COMING_ALARM) {
            supportFragmentManager.beginTransaction()
                .replace<AlarmListMoreFragment>(R.id.container_alarm_list_more)
        } else if (startPoint == ZOOM_IN_IMAGE) {
            supportFragmentManager.beginTransaction()
                .replace<ImageZoomInFragment>(R.id.container_alarm_list_more)
        }
    }

    private fun initStartFragmentData() {
        when (startPoint) {
            PASSED_ALARM -> {}
            UP_COMING_ALARM -> {}
            ZOOM_IN_IMAGE -> {
                alarmListExtraViewModel.updateZoomInImageUrl(imageUrl ?: "오류")
            }
        }
    }
}
