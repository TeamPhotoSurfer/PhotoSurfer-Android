package com.photosurfer.android.search_result.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo

class SearchResultViewModel : ViewModel() {
    fun isNoData(): Boolean = thumbnail.value?.size == 0


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

    private var _thumbnail = MutableLiveData<List<ThumbnailInfo?>>()
    val thumbnail: LiveData<List<ThumbnailInfo?>> = _thumbnail

    init {
        _thumbnail.value = listOf(
            ThumbnailInfo(
                1,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                2,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            )
        )
    }
}