package com.photosurfer.android.search_result.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.domain.entity.ThumbnailInfo

class SearchResultViewModel : ViewModel() {

    private var _originTagList = MutableLiveData<MutableList<TagInfo>>()
    val originTagList: LiveData<MutableList<TagInfo>> = _originTagList
    fun setOriginTagList(tagList: List<TagInfo>) {
        _originTagList.value = tagList.toMutableList()
    }

    private var _tagList: MutableLiveData<MutableList<TagInfo>> =
        MutableLiveData<MutableList<TagInfo>>()
    val tagList: LiveData<MutableList<TagInfo>> = _tagList
    val isTagListEmpty = MutableLiveData(tagList.value?.size == 0)
    fun setTempTagList(tagList: List<TagInfo>) {
        _tagList.value = tagList.toMutableList()
    }

    private var _thumbnail = MutableLiveData<MutableList<ThumbnailInfo?>>()
    val thumbnail: LiveData<MutableList<ThumbnailInfo?>> = _thumbnail
    val noThumbnailData = MutableLiveData(thumbnail.value?.size == 0)

    fun updateList(list: MutableList<Int>) {
        // for (i in 0 until list.size) {}
        // TODO 서버 response 값으로 교체
        _thumbnail.value = mutableListOf(
            ThumbnailInfo(
                1,
                "https://mblogthumb-phinf.pstatic.net/20151026_131/ddazero_1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            ),
            ThumbnailInfo(
                2,
                "https://mb1445793805984ouRO8_JPEG/dave1.jpg?type=w800"
            )
        )
        noThumbnailData.value = thumbnail.value?.size == 0
    }

    fun deleteTag(position: Int) {
        _tagList.value?.removeAt(position)
        isTagListEmpty.value = tagList.value?.size == 0
    }

    init {
        _thumbnail.value = mutableListOf(
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