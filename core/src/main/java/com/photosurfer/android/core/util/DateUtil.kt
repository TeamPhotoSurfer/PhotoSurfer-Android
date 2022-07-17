package com.photosurfer.android.core.util

import java.time.format.DateTimeFormatter
import java.util.*

object DateUtil {
    // 2022.7.9(토) 형식 formatter
    val dotDateFormatter =
        DateTimeFormatter.ofPattern("yyyy.MM.dd.(E)").withLocale(Locale.forLanguageTag("ko"))

    // 7월 9일 형식 formatter
    val monthDayDateFormatter =
        DateTimeFormatter.ofPattern("MM 월 dd 일")
}
