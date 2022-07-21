package com.photosurfer.android.alarm_list.moreinfo

import android.os.Bundle
import androidx.activity.viewModels
import com.photosurfer.android.alarm_list.AlarmListAdapter
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.PASSED_ALARM
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.START_POINT
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.UP_COMING_ALARM
import com.photosurfer.android.alarm_list.R
import com.photosurfer.android.alarm_list.databinding.ActivityAlarmListMoreBinding
import com.photosurfer.android.core.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class AlarmListExtraActivity :
    BaseActivity<ActivityAlarmListMoreBinding>(R.layout.activity_alarm_list_more) {

    private val alarmListExtraViewModel by viewModels<AlarmListExtraViewModel>()
    private var startPoint by Delegates.notNull<Int>()
    private lateinit var alarmListAdapter: AlarmListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExtraData()
        binding.startPoint = startPoint
        initStartFragmentData()
        initBackButtonClickListener()
        initAlarmListAdapter()
    }

    private fun initExtraData() {
        startPoint = intent.getIntExtra(START_POINT, 1)
    }

    private fun initStartFragmentData() {
        when (startPoint) {
            PASSED_ALARM -> {
                alarmListExtraViewModel.getPassedAlarmList()
            }
            UP_COMING_ALARM -> {
                alarmListExtraViewModel.getUpComingAlarmList()
            }
        }
    }

    private fun initBackButtonClickListener() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initAlarmListAdapter() {
        alarmListAdapter = AlarmListAdapter()
        binding.rvAlarmListMore.adapter = alarmListAdapter
        alarmListExtraViewModel.alarmData.observe(this) {
            alarmListAdapter.submitList(it)
        }
    }
}
