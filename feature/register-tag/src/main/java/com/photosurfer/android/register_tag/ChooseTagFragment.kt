package com.photosurfer.android.register_tag

import android.os.Bundle
import android.view.View
import com.photosurfer.android.core.base.BaseFragment
import com.photosurfer.android.register_tag.databinding.FragmentChooseTagBinding

class ChooseTagFragment : BaseFragment<FragmentChooseTagBinding>(R.layout.fragment_choose_tag) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(binding.etTag.text.isEmpty()) {
            // 비어있으면 뷰모델 isEmpty = true
        }

    }


    enum class Category(val categoryName: String) {
        PHOTOSURFER("포토서퍼"),
        CAFE("카페"),
        LIVETIP("생활꿀팁"),
        WISHLIST("위시리스트"),
        PLAN("휴학계획"),
        TRAVEL("여행");
    }


}