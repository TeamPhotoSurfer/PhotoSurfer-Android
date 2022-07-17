package com.photosurfer.android.core.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.photosurfer.android.core.util.DateUtil.monthDayDateFormatter
import com.photosurfer.android.shared.R.color.gray_20
import java.time.LocalDate

@BindingAdapter("app:goneUnless")
fun View.visibility(boolean: Boolean) {
    this.visibility = if (boolean) View.GONE else View.VISIBLE
}

@BindingAdapter("app:setDivisionHeaderText")
fun TextView.setDivisionHeaderText(dayState: Long) {
    if (dayState == -1L) {
        text = "오늘"
    } else if (dayState == -2L) {
        text = "내일"
    }
}

@BindingAdapter("app:shapeAbleSetImage")
fun ShapeableImageView.setImage(url: String?) {
    url?.let {
        Glide.with(this.context)
            .load(url)
            .placeholder(gray_20)
            .error(gray_20)
            .into(this)
    }
}

@BindingAdapter("app:setRepresentTagText")
fun TextView.setRepresentTagText(representTag: List<String>) {
    lateinit var tempString: String
    for (i in representTag) {
        if (i == representTag[0]) {
            tempString = "#$i"
        } else {
            tempString = "$tempString #$i"
        }
    }
}

@BindingAdapter("app:setDateToAlarmList")
fun TextView.setDateToAlarmList(localDate: LocalDate) {
    this.text = localDate.format(monthDayDateFormatter)
}
