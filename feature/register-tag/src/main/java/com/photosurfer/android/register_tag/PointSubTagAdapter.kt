package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.ItemTagPointSubBinding

class PointSubTagAdapter(
    private val tagsList: MutableList<TagInfo>
) :
    RecyclerView.Adapter<PointSubTagAdapter.SubTagViewHolder>() {

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
        holder: PointSubTagAdapter.SubTagViewHolder,
        position: Int
    ) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = tagsList.size

    inner class SubTagViewHolder(
        private val binding: ItemTagPointSubBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.tvTagName.text = tagsList[position].name
        }
    }

}