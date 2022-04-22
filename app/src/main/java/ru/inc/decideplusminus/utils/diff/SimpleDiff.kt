package ru.inc.decideplusminus.utils.diff

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO

class SimpleDiff: DiffUtil.ItemCallback<BaseSimpleItem>() {
    override fun areItemsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseSimpleItem, newItem: BaseSimpleItem): Boolean {
        return when(true) {
            (oldItem is SimpleVO && newItem is SimpleVO) -> { oldItem.name == newItem.name }
            else -> areItemsTheSame(oldItem, newItem)
        }
    }
}