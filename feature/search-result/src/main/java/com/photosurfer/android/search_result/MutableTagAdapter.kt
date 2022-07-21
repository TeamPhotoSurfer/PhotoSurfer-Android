package com.photosurfer.android.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.shared.databinding.ItemMutableChipBinding

class MutableTagAdapter(
    private val onItemClick: ((Int) -> Unit)
) : ListAdapter<TagInfo, MutableTagAdapter.ViewHolder>(MutableChipComparator()) {

    var isCancelable = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMutableChipBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mutableChip = getItem(position)
        holder.bind(mutableChip, onItemClick, isCancelable)
    }

    class ViewHolder(
        private val binding: ItemMutableChipBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(MutableChip: TagInfo, onItemClick: ((Int) -> Unit), isCancelable: Boolean) {
            binding.isCancelable = isCancelable
            binding.tagInfo = MutableChip
            binding.root.setOnClickListener { onItemClick(adapterPosition) }
        }
    }

    fun toggleCancelable() {
        isCancelable = !isCancelable
    }

    private class MutableChipComparator : DiffUtil.ItemCallback<TagInfo>() {
        override fun areItemsTheSame(oldItem: TagInfo, newItem: TagInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TagInfo, newItem: TagInfo): Boolean {
            return oldItem == newItem
        }
    }
}