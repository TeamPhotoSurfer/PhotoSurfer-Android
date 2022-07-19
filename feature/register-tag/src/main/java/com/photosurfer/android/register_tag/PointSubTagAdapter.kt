package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.core.util.ItemDiffCallback
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.ItemTagPointSubBinding

class PointSubTagAdapter(
    private val selectTag: ((TagInfo) -> Unit)? = null
) :
    ListAdapter<TagInfo, PointSubTagAdapter.SubTagViewHolder>(
        ItemDiffCallback<TagInfo>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.id == new.id }
        )
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubTagViewHolder {
        val binding: ItemTagPointSubBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag_point_sub,
            parent,
            false
        )
        return SubTagViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: SubTagViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position), selectTag)
    }

    inner class SubTagViewHolder(
        private val binding: ItemTagPointSubBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo, selectTag: ((TagInfo) -> Unit)? = null) {
            binding.tagInfo = data
            binding.clWholeTag.setOnClickListener {
                selectTag?.invoke(data)
            }
        }
    }
}