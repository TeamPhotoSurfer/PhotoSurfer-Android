package com.photosurfer.android.alarm_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.replace
import com.photosurfer.android.alarm_list.AlarmListMainFragment.Companion.ZOOM_IN_IMAGE
import com.photosurfer.android.alarm_list.databinding.FragmentAlarmListMoreBinding
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.domain.entity.AlarmElement
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class AlarmListMoreFragment :
    BaseFragment<FragmentAlarmListMoreBinding>(R.layout.fragment_alarm_list_more) {

    private lateinit var alarmListAdapter: AlarmListAdapter
    private val alarmListExtraViewModel by activityViewModels<AlarmListExtraViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAlarmListAdapter() {
        alarmListAdapter = AlarmListAdapter(::rvAlarmListItemClickListener)
        binding.rvAlarmListMore.adapter = alarmListAdapter
        // 리스트 업데이트 코드
        // 현재는 더미
        alarmListAdapter.submitList(
            listOf(
                AlarmElement(
                    id = 1L,
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
                    id = 2L,
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

    private fun rvAlarmListItemClickListener(url: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace<ImageZoomInFragment>(R.id.container_alarm_list_more)
        alarmListExtraViewModel.updateFragmentState(ZOOM_IN_IMAGE)
        alarmListExtraViewModel.updateZoomInImageUrl(url)
    }
}
