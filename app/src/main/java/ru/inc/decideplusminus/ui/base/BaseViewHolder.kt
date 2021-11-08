package ru.inc.decideplusminus.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<I>(view: ViewBinding) : RecyclerView.ViewHolder(view.root) {
    abstract fun bind(model: I)
}