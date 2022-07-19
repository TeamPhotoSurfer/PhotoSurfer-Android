package com.photosurfer.android.register_tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.ChooseTagInfo
import com.photosurfer.android.domain.entity.TagInfo

class ChooseTagViewModel : ViewModel() {
    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput

    private var _inputList: MutableList<ChooseTagInfo> = mutableListOf()
    //val inputList: MutableList<ChooseTagInfo> = _inputList

    val inputList: MutableList<TagInfo> = mutableListOf()
    val recentList: MutableList<TagInfo> = mutableListOf()
    val oftenList: MutableList<TagInfo> = mutableListOf()
    val platformList: MutableList<TagInfo> = mutableListOf()

    fun setTagList() {
        recentList.addAll(
            listOf(
                TagInfo(1L, "포토서퍼"),
                TagInfo(2L, "카페"),
                TagInfo(1L, "생활꿀팁"),
                TagInfo(2L, "위시리스트"),
                TagInfo(1L, "휴학"),
                TagInfo(2L, "여행")
            )
        )
        oftenList.addAll(
            listOf(
                TagInfo(1L, "좋은노래"),
                TagInfo(2L, "솝트"),
                TagInfo(1L, "전시회"),
                TagInfo(2L, "그래픽디자인"),
                TagInfo(1L, "포토서퍼"),
                TagInfo(2L, "인턴")
            )
        )
        platformList.addAll(
            listOf(
                TagInfo(1L, "카카오톡"),
                TagInfo(2L, "유튜브"),
                TagInfo(1L, "인스타그램"),
                TagInfo(2L, "쇼핑몰"),
                TagInfo(1L, "커뮤니티"),
                TagInfo(2L, "기타")
            )
        )

    }

    fun setInputList(value: MutableList<ChooseTagInfo>) {
        _inputList = value
    }

    fun setEmptyInput(value: Int) {
        _isEmptyInput.value = value
    }

}