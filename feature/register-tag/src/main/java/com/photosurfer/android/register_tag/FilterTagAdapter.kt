package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.core.util.ItemDiffCallback
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.shared.databinding.ItemTagPointSubBinding

/*
class FilterTagAdapter () : ListAdapter<TagInfo, FilterTagAdapter.FilterViewHolder>(
    ItemDiffCallback<TagInfo>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterTagAdapter.FilterViewHolder {
        val binding: ItemTagPointSubBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            item_tag
        )
    }

    override fun onBindViewHolder(holder: FilterTagAdapter.FilterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class FilterTagViewHoler(
        private val binding: ItemTagPointSubBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo) {
            binding.tagInfo = data
        }
    }

}
 */