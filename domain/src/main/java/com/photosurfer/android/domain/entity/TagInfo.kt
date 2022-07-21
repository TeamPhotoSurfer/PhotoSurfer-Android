package com.photosurfer.android.domain.entity

import java.io.Serializable

data class TagInfo(
    val id: Int,
    val name: String
) : Serializable

data class SerializeTagInfoList(
    val TagInfoList: List<TagInfo>
) : Serializable
