package com.photosurfer.android.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.databinding.ItemTagBinding

class TagListAdapter : RecyclerView.Adapter<TagListAdapter.TagListViewHolder>() {
    private var _tagList = mutableListOf<TagInfo>()
    var tagList: MutableList<TagInfo> = _tagList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {
        val binding: ItemTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag,
            parent,
            false
        )
        return TagListViewHolder(binding)
    }

    inner class TagListViewHolder(
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo, position: Int) {
            binding.tagInfo = data
        }
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.onBind(tagList[position], position)
    }

    override fun getItemCount(): Int = _tagList.size
}