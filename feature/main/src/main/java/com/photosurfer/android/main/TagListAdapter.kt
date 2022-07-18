package com.photosurfer.android.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.TagListAdapter.*
import com.photosurfer.android.main.databinding.ItemTagBinding

class TagListAdapter(
    private val threeDotClickListener: ((Int, ImageView) -> Unit)? = null
) : ListAdapter<TagInfo, TagListViewHolder>(TagComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {
        val binding: ItemTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag,
            parent,
            false
        )
        return TagListViewHolder(binding)
    }

    class TagListViewHolder(
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: TagInfo,
            position: Int,
            threeDotClickListener: ((Int, ImageView) -> Unit)? = null
        ) {
            binding.tagInfo = data

            binding.ivStar.setOnClickListener {
                binding.ivStar.isSelected = binding.ivStar.isSelected != true
            }

            binding.ivThreedots.setOnClickListener {
                threeDotClickListener?.invoke(position, binding.ivThreedots)
            }
        }
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.onBind(getItem(position), position, threeDotClickListener)
    }

    private class TagComparator : DiffUtil.ItemCallback<TagInfo>() {
        override fun areItemsTheSame(oldItem: TagInfo, newItem: TagInfo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TagInfo, newItem: TagInfo) =
            oldItem == newItem
    }
}