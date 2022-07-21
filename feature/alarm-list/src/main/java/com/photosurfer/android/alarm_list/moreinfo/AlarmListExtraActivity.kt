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
import com.photosurfer.android.domain.entity.AlarmElement
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
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
            PASSED_ALARM -> {}
            UP_COMING_ALARM -> {}
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
        // 리스트 업데이트 코드
        // 현재는 더미
//        alarmListAdapter.submitList(
//            listOf(
//                AlarmElement(
//                    id = 1L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                ),
//                AlarmElement(
//                    id = 1L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                ),
//                AlarmElement(
//                    id = 2L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                ),
//                AlarmElement(
//                    id = 3L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                ),
//                AlarmElement(
//                    id = 2L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                ),
//                AlarmElement(
//                    id = 4L,
//                    pushDate = LocalDate.now(),
//                    tags = listOf("이", "창", "환"),
//                    imageURL = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRSihF%2FbtrESP6CdQz%2FjQMTqA5fz1kiBbKJtYtxJ0%2Fimg.jpg",
//                    memo = "알랄라 살랄라"
//                )
//            )
//        )
    }
}
