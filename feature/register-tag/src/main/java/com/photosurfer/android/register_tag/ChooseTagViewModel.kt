package com.photosurfer.android.register_tag

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photosurfer.android.core.base.BaseViewModel
import com.photosurfer.android.core.util.Event
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.request.DomainChooseTagRequest
import com.photosurfer.android.domain.repository.ChooseTagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChooseTagViewModel @Inject constructor(
    private val chooseTagRepository: ChooseTagRepository
) : BaseViewModel() {
    private var _isEmptyInput = MutableLiveData<Int>()
    val isEmptyInput: LiveData<Int> get() = _isEmptyInput
    var inputList: MutableList<TagInfo> = mutableListOf()
    val recentList: MutableList<TagInfo> = mutableListOf()
    val oftenList: MutableList<TagInfo> = mutableListOf()
    val platformList: MutableList<TagInfo> = mutableListOf()

    private val _chooseTagSuccess = MutableLiveData<Event<Boolean>>()
    val chooseTagSuccess: LiveData<Event<Boolean>> = _chooseTagSuccess

    private val _chooseTagFailure = MutableLiveData<Event<Boolean>>()
    val chooseTagFailure: LiveData<Event<Boolean>> = _chooseTagFailure


    val imageFile: File
        get() {
            TODO()
        }

    fun setTagList() {
        recentList.addAll(
            listOf(
                TagInfo(7, "포토서퍼"),
                TagInfo(8, "카페"),
                TagInfo(9, "생활꿀팁"),
                TagInfo(10, "위시리스트"),
                TagInfo(11, "휴학"),
                TagInfo(12, "여행")
            )
        )
        oftenList.addAll(
            listOf(
                TagInfo(13, "좋은노래"),
                TagInfo(14, "솝트"),
                TagInfo(15, "전시회"),
                TagInfo(16, "그래픽디자인"),
                TagInfo(17, "포토서퍼"),
                TagInfo(18, "인턴")
            )
        )
        platformList.addAll(
            listOf(
                TagInfo(1, "카카오톡"),
                TagInfo(2, "유튜브"),
                TagInfo(3, "인스타그램"),
                TagInfo(4, "쇼핑몰"),
                TagInfo(5, "커뮤니티"),
                TagInfo(6, "기타")
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

    // TODO: GET 서버 로직 붙이고 추가할 예정
//    fun postChooseTag() {
//        viewModelScope.launch {
//            chooseTagRepository.postTag(
//                DomainChooseTagRequest(
//                    // TODO : imageFile 멀티파트 처리한 거 넣어줘야함
//                    file = imageFile,
//                    tags = inputList
//                )
//            ).onSuccess {
//                _chooseTagSuccess.postValue(Event(true))
//            }.onFailure {
//                _chooseTagFailure.postValue(Event(true))
//            }
//        }
//    }

}