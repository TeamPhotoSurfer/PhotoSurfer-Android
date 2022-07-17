package com.photosurfer.android.register_tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.register_tag.databinding.ItemTagBinding
import com.photosurfer.android.register_tag.databinding.ItemTagMsgBinding

class TagAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val tagList = mutableListOf<TagInfo>(TagInfo(1, "aa"))
    val RECENT_MSG = 0
    val RECENT_TAG = 1
    val OFTEN_MSG = 2
    val OFTEN_TAG = 3
    val PLATFORM_MSG = 4
    val PLATFORM_TAG = 5
    private lateinit var itemTagBinding: ItemTagBinding
    private lateinit var itemRecentTagMsgBinding: ItemTagMsgBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            RECENT_MSG -> {
                 itemRecentTagMsgBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_tag_msg,
                    parent,
                    false
                )
                RecentTagMsgViewHolder(itemRecentTagMsgBinding)
            }
            RECENT_TAG -> {
                itemTagBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_tag,
                    parent,
                    false
                )
                RecentTagViewHolder(itemTagBinding)
            }
            else -> {
                throw IllegalArgumentException("알 수 없는 view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is RecentTagMsgViewHolder) {
            holder.binding.tvRecent.text = "자주... 추가........"
        }
        else if(holder is RecentTagViewHolder) {
            holder.binding.tvTag.text = tagList[position].name
        }
    }

    override fun getItemCount(): Int = tagList.size

    override fun getItemViewType(position: Int): Int {
        return RECENT_MSG
    }

    inner class RecentTagViewHolder(val binding: ItemTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun onBind() {

            }

    }

    inner class RecentTagMsgViewHolder(val binding: ItemTagMsgBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

}