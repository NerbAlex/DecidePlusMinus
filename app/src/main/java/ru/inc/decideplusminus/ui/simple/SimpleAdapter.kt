package ru.inc.decideplusminus.ui.simple

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.inc.decideplusminus.ui.base.BaseViewHolder
import ru.inc.decideplusminus.ui.base.ViewHoldersFactory

class SimpleAdapter(private val listeners: SimpleAdapterListeners) :
    ListAdapter<BaseSimpleItem, BaseViewHolder<BaseSimpleItem>>(SimpleDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseSimpleItem> =
        ViewHoldersFactory.create(viewType, parent, listeners)

    override fun onBindViewHolder(holder: BaseViewHolder<BaseSimpleItem>, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = getItem(position).type
}