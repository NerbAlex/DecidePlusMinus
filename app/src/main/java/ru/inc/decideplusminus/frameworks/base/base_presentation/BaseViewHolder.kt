package ru.inc.decideplusminus.frameworks.base.base_presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<I>(view: ViewBinding) : RecyclerView.ViewHolder(view.root) {
    abstract fun bind(model: I)
}