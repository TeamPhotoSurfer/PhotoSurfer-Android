package com.photosurfer.android.data.remote.mapper

import com.photosurfer.android.data.remote.model.response.Push
import com.photosurfer.android.domain.entity.AlarmElement
import java.time.LocalDate
import javax.inject.Inject

class PushMapper @Inject constructor() {

    fun toAlarmElement(push: Push): AlarmElement =
        AlarmElement(
            id = push.id,
            pushDate = LocalDate.parse(push.pushDate),
            tags = push.tags,
            imageURL = push.imageUrl,
            memo = push.memo,
            photoId = push.photoId
        )
}
