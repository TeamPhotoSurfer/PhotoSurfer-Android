package com.photosurfer.android.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.SavedTag
import com.photosurfer.android.main.TagListAdapter.TagListViewHolder
import com.photosurfer.android.main.databinding.ItemTagBinding

class TagListAdapter(
    private val threeDotClickListener: ((Int, ImageView) -> Unit)? = null,
    private val startClickListener: ((SavedTag) -> Unit)
) : ListAdapter<SavedTag, TagListViewHolder>(TagComparator()) {

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
            data: SavedTag,
            position: Int,
            threeDotClickListener: ((Int, ImageView) -> Unit)? = null,
            startClickListener: ((SavedTag) -> Unit)
        ) {
            binding.savedTag = data

            binding.ivStar.setOnClickListener {
                binding.ivStar.isSelected = binding.ivStar.isSelected != true
                // TODO : 누르면 서버 통신
                if (binding.ivStar.isSelected) {
                    // 즐찾 설정
                } else {
                    // 즐찾 취소
                }
            }

            binding.root.setOnClickListener { startClickListener(data) }

            binding.ivThreedots.setOnClickListener {
                threeDotClickListener?.invoke(position, binding.ivThreedots)
            }
        }
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.onBind(getItem(position), position, threeDotClickListener, startClickListener)
    }

    private class TagComparator : DiffUtil.ItemCallback<SavedTag>() {
        override fun areItemsTheSame(oldItem: SavedTag, newItem: SavedTag) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SavedTag, newItem: SavedTag) =
            oldItem == newItem
    }
}