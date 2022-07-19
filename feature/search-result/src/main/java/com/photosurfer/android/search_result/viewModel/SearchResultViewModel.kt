package com.photosurfer.android.search_result.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo
import timber.log.Timber

class SearchResultViewModel : ViewModel() {

    private var _originTagList = MutableLiveData(
        mutableListOf(
            TagInfo(0, "포토서퍼"),
            TagInfo(1, "카페"),
            TagInfo(2, "생활꿀팁"),
            TagInfo(3, "위시리스트"),
            TagInfo(4, "휴학계획"),
            TagInfo(5, "여행")
        )
    )
    val originTagList: LiveData<MutableList<TagInfo>> = _originTagList

    private var _thumbnail = MutableLiveData<List<ThumbnailInfo?>>()
    val thumbnail: LiveData<List<ThumbnailInfo?>> = _thumbnail

    val noThumbnailData = MutableLiveData(thumbnail.value?.size == 0)

    fun updateList(list: MutableList<Int>) {
        // for (i in 0 until list.size) {}
        _thumbnail.value = listOf()     // TODO 서버 response 값으로 교체
        noThumbnailData.value = thumbnail.value?.size == 0
        Timber.d("please ${thumbnail.value} ${noThumbnailData.value}")
    }

    init {
        _thumbnail.value = listOf(
            ThumbnailInfo(
                1,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                2,
                "https://mb1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                3,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                4,
                "https://mblogthumb-phinf.pstatic.net/201uRO8_JPEG/dave1.jpg?type=w800"
            ),
        )
    }
}