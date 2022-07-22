package com.photosurfer.android.search_result.detailimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.search_result.detailimage.DetailImageActivity.Companion.ADD
import com.photosurfer.android.shared.databinding.ItemMutableChipDetailBinding

class DetailImageAdapter() :
    ListAdapter<TagInfo, DetailImageAdapter.DetailImageViewHolder>(MutableChipComparator()) {

    private var processState = ADD

    fun updateProcessState(state: String) {
        processState = state
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMutableChipDetailBinding.inflate(layoutInflater, parent, false)
        return DetailImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        val mutableChip = getItem(position)
        holder.bind(mutableChip)
    }

    class DetailImageViewHolder(
        private val binding: ItemMutableChipDetailBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(MutableChip: TagInfo) {
            binding.tagInfo = MutableChip
        }
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
