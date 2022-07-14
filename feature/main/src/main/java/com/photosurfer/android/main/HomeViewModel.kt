package com.photosurfer.android.main

import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.TagInfo

class HomeViewModel : ViewModel() {
    val isOftenTagMoreThanZero = true
    val fakeOftenTagList = listOf(
        TagInfo(0, "포토서퍼"),
        TagInfo(1, "카페"),
        TagInfo(2, "생활꿀팁"),
        TagInfo(3, "위시리스트"),
        TagInfo(4, "휴학계획"),
        TagInfo(5, "여행")
    )
    val fakeOftenTagLongList = listOf(
        TagInfo(0, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(1, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(2, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(3, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(4, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼"),
        TagInfo(5, "포토서퍼포토서퍼포토서퍼포토서퍼포토서퍼")
    )
}