package com.photosurfer.android.domain.entity

import java.time.LocalDate

data class AlarmInfo(
    val alarmList: MutableList<AlarmElement>,
    val passedCount: Int,
    val upComingCount: Int,
    val urgentCount: Int
)

data class AlarmElement(
    val id: Int,
    val pushDate: LocalDate,
    val tags: List<TagInfo>,
    val imageURL: String?,
    val memo: String,
    val photoId: Int?
)
