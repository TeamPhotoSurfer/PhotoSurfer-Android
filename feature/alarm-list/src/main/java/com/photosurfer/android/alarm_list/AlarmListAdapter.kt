package com.photosurfer.android.alarm_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.photosurfer.android.alarm_list.databinding.ItemAlarmContentBinding
import com.photosurfer.android.alarm_list.databinding.ItemDivisionDayHeaderBinding
import com.photosurfer.android.core.util.ItemDiffCallback
import com.photosurfer.android.domain.entity.AlarmElement

class AlarmListAdapter(
    private val itemCLickListener: ((String) -> Unit)? = null
) : ListAdapter<AlarmElement, RecyclerView.ViewHolder>(
    ItemDiffCallback<AlarmElement>(
        onContentsTheSame = { old, new -> old == new },
        onItemsTheSame = { old, new -> old.id == new.id }
    )
) {

    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).id == TODAY_ID || getItem(position).id == TOMORROW_ID) {
            DIVISION_DAY_HEADER
        } else {
            ALARM_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }

        return when (viewType) {
            DIVISION_DAY_HEADER -> {
                val binding = ItemDivisionDayHeaderBinding.inflate(inflater, parent, false)
                DivisionDayHeaderViewHolder(binding)
            }
            ALARM_CONTENT -> {
                val binding = ItemAlarmContentBinding.inflate(inflater, parent, false)
                AlarmListElementViewHolder(binding)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewType error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DivisionDayHeaderViewHolder) {
            holder.onBind(getItem(position))
        } else if (holder is AlarmListElementViewHolder) {
            holder.onBind(getItem(position), itemCLickListener)
        }
    }

    class AlarmListElementViewHolder(private val binding: ItemAlarmContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AlarmElement, itemCLickListener: ((String) -> Unit)? = null) {
            binding.alarmData = data
            binding.root.setOnClickListener {
                itemCLickListener?.invoke(data.imageURL)
            }
        }
    }

    class DivisionDayHeaderViewHolder(private val binding: ItemDivisionDayHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: AlarmElement) {
            binding.alarmData = data
        }
    }

    companion object {
        const val TODAY_ID = -1L
        const val TOMORROW_ID = -2L
        const val DIVISION_DAY_HEADER = 1
        const val ALARM_CONTENT = 2
    }
}
