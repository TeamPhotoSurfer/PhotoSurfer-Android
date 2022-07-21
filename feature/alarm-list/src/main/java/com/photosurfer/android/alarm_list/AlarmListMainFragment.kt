package com.photosurfer.android.alarm_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.photosurfer.android.alarm_list.databinding.FragmentAlarmListMainBinding
import com.photosurfer.android.alarm_list.moreinfo.AlarmListExtraActivity
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.AlarmElement
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmListMainFragment :
    BaseFragment<FragmentAlarmListMainBinding>(R.layout.fragment_alarm_list_main) {

    private val alarmListMainViewModel by viewModels<AlarmListMainViewModel>()
    private lateinit var alarmListAdapter: AlarmListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAlarmInfoData()
        initAlarmCountData()
        initAlarmListAdapter()
        setPassedAlarmClickListener()
        setUpComingAlarmClickListener()
        initAlarmListAdapterData()
    }

    private fun initAlarmListAdapter() {
        alarmListAdapter = AlarmListAdapter()
        binding.rvAlarmList.adapter = alarmListAdapter
    }

    private fun initAlarmListAdapterData() {
        alarmListMainViewModel.urgentAlarmList.observe(viewLifecycleOwner) {
            alarmListAdapter.submitList(it ?: listOf<AlarmElement>())
        }
    }

    private fun initAlarmInfoData() {
        alarmListMainViewModel.getUrgentAlarmList()
    }

    private fun initAlarmCountData() {
        alarmListMainViewModel.passedAlarmCount.observe(viewLifecycleOwner) {
            binding.passedAlarm = it
        }
        alarmListMainViewModel.upComingAlarmCount.observe(viewLifecycleOwner) {
            binding.upComingAlarm = it
        }
        alarmListMainViewModel.urgentAlarmCount.observe(viewLifecycleOwner) {
            binding.urgentAlarm = it
        }
    }

    private fun setPassedAlarmClickListener() {
        binding.tvPassedMore.setOnClickListener {
            val intent = Intent(requireActivity(), AlarmListExtraActivity::class.java).apply {
                putExtra(START_POINT, PASSED_ALARM)
            }
            startActivity(intent)
        }
    }

    private fun setUpComingAlarmClickListener() {
        binding.tvUpComingMore.setOnClickListener {
            val intent = Intent(requireActivity(), AlarmListExtraActivity::class.java).apply {
                putExtra(START_POINT, UP_COMING_ALARM)
            }
            startActivity(intent)
        }
    }

    companion object {
        const val PASSED_ALARM = 1
        const val UP_COMING_ALARM = 2
        const val CLICKABLE_STATE = "CLICKABLE_STATE"
        const val START_POINT = "START_POINT"
        const val ZOOM_IN_IMAGE_URL = "ZOOM_IN_IMAGE_URL"
        const val PUSH_ID = "PUSH_ID"
    }
}
