package ru.inc.decideplusminus.ui.simple

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class SimpleDiff: DiffUtil.ItemCallback<BaseSimpleItem>() {
    override fun areItemsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return when(true) {
            (oldItem is SimpleVO && newItem is SimpleVO) -> { oldItem == newItem }
            else -> areItemsTheSame(oldItem, newItem)
        }
    }
}