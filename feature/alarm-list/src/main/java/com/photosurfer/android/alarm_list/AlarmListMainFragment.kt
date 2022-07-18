package com.photosurfer.android.alarm_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.photosurfer.android.alarm_list.databinding.FragmentAlarmListMainBinding
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.AlarmElement
import java.time.LocalDate

class AlarmListMainFragment :
    BaseFragment<FragmentAlarmListMainBinding>(R.layout.fragment_alarm_list_main) {

    private lateinit var alarmListAdapter: AlarmListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAlarmListAdapter()
        passedMoreButtonClickListener()
        upComingMoreButtonClickListener()
    }

    private fun initAlarmListAdapter() {
        alarmListAdapter = AlarmListAdapter(::rvAlarmListItemClickListener)
        binding.rvAlarmList.adapter = alarmListAdapter
        // 리스트 업데이트 하는 코드 필요
        // 현재는 더미
        alarmListAdapter.submitList(
            listOf(
                AlarmElement(
                    id = -1L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                ),
                AlarmElement(
                    id = 1L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                ),
                AlarmElement(
                    id = 2L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                ),
                AlarmElement(
                    id = 3L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                ),
                AlarmElement(
                    id = -2L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                ),
                AlarmElement(
                    id = 4L,
                    pushDate = LocalDate.now(),
                    tags = listOf("이", "창", "환"),
                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
                    memo = "알랄라 살랄라"
                )
            )
        )
    }

    private fun passedMoreButtonClickListener() {
        binding.tvPassedMore.setOnClickListener {
            val intent = Intent(requireActivity(), AlarmListExtraActivity::class.java).apply {
                putExtra(START_POINT, PASSED_ALARM)
            }
            startActivity(intent)
        }
    }

    private fun upComingMoreButtonClickListener() {
        binding.tvUpComingMore.setOnClickListener {
            val intent = Intent(requireActivity(), AlarmListExtraActivity::class.java).apply {
                putExtra(START_POINT, UP_COMING_ALARM)
            }
            startActivity(intent)
        }
    }

    private fun rvAlarmListItemClickListener(url: String) {
        val intent = Intent(requireActivity(), AlarmListExtraActivity::class.java).apply {
            putExtra(START_POINT, ZOOM_IN_IMAGE)
            putExtra(ZOOM_IN_IMAGE_URL, url)
        }
        startActivity(intent)
    }

    companion object {
        const val PASSED_ALARM = 1
        const val UP_COMING_ALARM = 2
        const val ZOOM_IN_IMAGE = 3
        const val CHECK_ALARM = 4
        const val START_POINT = "START_POINT"
        const val ZOOM_IN_IMAGE_URL = "ZOOM_IN_IMAGE_URL"
    }
}
