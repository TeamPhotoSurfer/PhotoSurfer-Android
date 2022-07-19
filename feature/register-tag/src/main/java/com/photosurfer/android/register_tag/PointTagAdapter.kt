package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.core.util.ItemDiffCallback
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.ItemTagPointBinding

class PointTagAdapter(
    private val deleteTag: ((TagInfo) -> Unit)? = null
) :
    ListAdapter<TagInfo, PointTagAdapter.PointTagViewHolder>(
        ItemDiffCallback<TagInfo>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.id == new.id }
        )
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PointTagViewHolder {
        val binding: ItemTagPointBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag_point,
            parent,
            false
        )
        return PointTagViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PointTagViewHolder,
        position: Int
    ) {
        holder.onBind(getItem(position), deleteTag)
    }

    inner class PointTagViewHolder(
        private val binding: ItemTagPointBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo, selectTag: ((TagInfo) -> Unit)? = null) {
            binding.tagInfo = data
            binding.ivDelete.setOnClickListener {
                selectTag?.invoke(data)
                notifyDataSetChanged()
            }
        }
    }
}