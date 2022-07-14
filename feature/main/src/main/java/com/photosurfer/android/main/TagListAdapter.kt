package com.photosurfer.android.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.domain.entity.TagInfo
import com.photosurfer.android.main.databinding.ItemTagBinding

class TagListAdapter(
    private var optionsMenuClickListener: OptionsMenuClickListener
): RecyclerView.Adapter<TagListAdapter.TagListViewHolder>() {
    private var _tagList = mutableListOf<TagInfo>()
    var tagList: MutableList<TagInfo> = _tagList

    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {
        val binding: ItemTagBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_tag,
            parent,
            false
        )
        return TagListViewHolder(binding)
    }

    inner class TagListViewHolder (
        private val binding: ItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TagInfo, position: Int) {
            binding.tagInfo = data

            binding.ivStar.setOnClickListener {
                binding.ivStar.isSelected = binding.ivStar.isSelected != true
            }

            binding.ivThreedots.setOnClickListener {
                // 점 3개 누르면 옵션 창 뜨기
                optionsMenuClickListener.onOptionsMenuClicked(position)
            }

        }
    }



    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        holder.onBind(tagList[position], position)
    }

    override fun getItemCount(): Int = _tagList.size
}