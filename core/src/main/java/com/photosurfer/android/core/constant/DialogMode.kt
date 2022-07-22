package com.photosurfer.android.core.constant

enum class DialogMode(
    var title: String,
    var tag: String? = null,
    var description: String? = null,
    var confirmText: String
) {
    DELETE_TAG(
        title = "태그를 삭제하시겠습니까?",
        confirmText = "삭제"
    ),
    DELETE_LAST_TAG(
        title = "태그를 삭제하시겠습니까?",
        description = "마지막 태그를 삭제하면 사진도 포토서퍼에서 지워집니다.",
        confirmText = "삭제"
    ),
    DELETE_ONE_PHOTO(
        title = "사진을 삭제하시겠습니까?",
        confirmText = "삭제"
    ),
    DELETE_SELECTED_PHOTO(
        title = "선택한 사진을 삭제하시겠습니까?",
        confirmText = "삭제"
    );

    companion object {
        fun findClassByDialogMode(name: String): DialogMode? =
            values().find { it.name == name }
    }
}