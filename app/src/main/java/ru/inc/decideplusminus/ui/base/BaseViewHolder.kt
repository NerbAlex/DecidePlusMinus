package ru.inc.decideplusminus.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<I>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: I)
}