package com.photosurfer.android.domain.entity

import java.time.LocalDate

data class AlarmInfo(
    val alarmList: MutableList<AlarmElement>,
    val lastCount: Int,
    val upComingCount: Int,
    val urgentCount: Int
)

data class AlarmElement(
    val id: Long,
    val pushDate: LocalDate,
    val tags: List<String>,
    val imageURL: String,
    val memo: String
)