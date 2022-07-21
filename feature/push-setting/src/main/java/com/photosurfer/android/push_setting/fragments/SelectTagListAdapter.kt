package com.photosurfer.android.push_setting.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.core.util.ItemDiffCallback
import com.photosurfer.android.core.util.PhotoSurferSnackBar
import com.photosurfer.android.core.util.PhotoSurferSnackBar.Companion.SELECT_TAG_FRAGMENT
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.push_setting.databinding.ItemSelectTagBinding

class SelectTagListAdapter(
    var tempList: MutableList<TagInfo>,
    private val updateTempRepresentTagList: ((MutableList<TagInfo>) -> Unit)? = null
) :
    ListAdapter<TagInfo, SelectTagListAdapter.SelectTagRecyclerViewHolder>(
        ItemDiffCallback<TagInfo>(
            onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old.id == new.id }
        )
    ) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTagRecyclerViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemSelectTagBinding.inflate(inflater, parent, false)

        return SelectTagRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectTagRecyclerViewHolder, position: Int) {
        holder.onBind(getItem(position), updateTempRepresentTagList)
    }

    inner class SelectTagRecyclerViewHolder(
        private val binding: ItemSelectTagBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: TagInfo,
            updateTempRepresentTagList: ((MutableList<TagInfo>) -> Unit)? = null
        ) {
            binding.tagInfo = data
            if (tempList.contains(data)) {
                binding.cbCheckImage.isChecked = true
            }
            binding.root.setOnClickListener {
                val tempConclusion = tempList.remove(data)
                if (!tempConclusion) {
                    if (tempList.size < 3) {
                        tempList.add(data)
                        binding.cbCheckImage.isChecked = true
                    } else {
                        PhotoSurferSnackBar.make(it, SELECT_TAG_FRAGMENT).show()
                    }
                } else {
                    binding.cbCheckImage.isChecked = false
                }
                updateTempRepresentTagList?.invoke(tempList)
            }
        }
    }
}
