package ru.inc.decideplusminus.ui.simple

import androidx.recyclerview.widget.DiffUtil

class SimpleDiff: DiffUtil.ItemCallback<BaseSimpleItem>() {
    override fun areItemsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return oldItem == newItem
    }
}