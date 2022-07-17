package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.ItemTagBinding

class TagRecyclerViewAdapter :
    RecyclerView.Adapter<TagRecyclerViewAdapter.TagRecyclerViewHolder>() {

    private var _tagList = mutableListOf<TagInfo>()
    var tagList: MutableList<TagInfo> = _tagList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TagRecyclerViewHolder {
        val binding: ItemTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag,
            parent,
            false
        )
        return TagRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TagRecyclerViewAdapter.TagRecyclerViewHolder,
        position: Int
    ) {
        holder.onBind(tagList[position], position)
    }

    override fun getItemCount(): Int = _tagList.size

    inner class TagRecyclerViewHolder(
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo, position: Int) {
            binding.tvTag.text = tagList[position].name
        }
    }

}