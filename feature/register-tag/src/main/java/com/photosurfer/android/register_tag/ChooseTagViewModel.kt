package com.photosurfer.android.register_tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.TagInfo

class ChooseTagViewModel : ViewModel() {
    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput
    var inputList: MutableList<TagInfo> = mutableListOf()
    val recentList: MutableList<TagInfo> = mutableListOf()
    val oftenList: MutableList<TagInfo> = mutableListOf()
    val platformList: MutableList<TagInfo> = mutableListOf()

    fun setTagList() {
        recentList.addAll(
            listOf(
                TagInfo(7L, "포토서퍼"),
                TagInfo(8L, "카페"),
                TagInfo(9L, "생활꿀팁"),
                TagInfo(10L, "위시리스트"),
                TagInfo(11L, "휴학"),
                TagInfo(12L, "여행")
            )
        )
        oftenList.addAll(
            listOf(
                TagInfo(13L, "좋은노래"),
                TagInfo(14L, "솝트"),
                TagInfo(15L, "전시회"),
                TagInfo(16L, "그래픽디자인"),
                TagInfo(17L, "포토서퍼"),
                TagInfo(18L, "인턴")
            )
        )
        platformList.addAll(
            listOf(
                TagInfo(1L, "카카오톡"),
                TagInfo(2L, "유튜브"),
                TagInfo(3L, "인스타그램"),
                TagInfo(4L, "쇼핑몰"),
                TagInfo(5L, "커뮤니티"),
                TagInfo(6L, "기타")
            )
        )

    }

    fun setEmptyInput(value: Int) {
        _isEmptyInput.value = value
    }

    fun selectTag(item: TagInfo) {
        inputList.add(item)
    }

    fun deleteTag(item: TagInfo) {
        inputList.remove(item)
    }
}