package com.photosurfer.android.search_result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.ThumbnailInfo
import com.photosurfer.android.search_result.databinding.ItemPhotoThumbnailBinding


class ThumbnailAdapter(
    private val onItemClick: ((ThumbnailInfo) -> Unit)
) : ListAdapter<ThumbnailInfo, ThumbnailAdapter.ViewHolder>(ThumbNailComparator()) {

    var isSelectable = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoThumbnailBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    fun toggleSelectable() {
        isSelectable = !isSelectable
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thumbnail = getItem(position)
        holder.bind(thumbnail, onItemClick, isSelectable)
    }

    class ViewHolder(
        private val binding: ItemPhotoThumbnailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            thumbnail: ThumbnailInfo,
            onItemClick: ((ThumbnailInfo) -> Unit),
            isSelectable: Boolean
        ) {
            binding.data = thumbnail
//            binding.isSelectable = isSelectable
            binding.root.setOnClickListener { onItemClick(thumbnail) }
        }
    }

    private class ThumbNailComparator : DiffUtil.ItemCallback<ThumbnailInfo>() {
        override fun areItemsTheSame(oldItem: ThumbnailInfo, newItem: ThumbnailInfo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ThumbnailInfo, newItem: ThumbnailInfo) =
            oldItem == newItem
    }
}