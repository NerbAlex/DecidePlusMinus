package ru.inc.decideplusminus.presentation.ui.simple

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewHolder
import ru.inc.decideplusminus.utils.ViewHoldersFactory
import ru.inc.decideplusminus.utils.diff.SimpleDiff

class SimpleAdapter(private val listeners: SimpleAdapterListener) :
    ListAdapter<BaseSimpleItem, BaseViewHolder<BaseSimpleItem>>(SimpleDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseSimpleItem> =
        ViewHoldersFactory.create(viewType, parent, listeners)

    override fun onBindViewHolder(holder: BaseViewHolder<BaseSimpleItem>, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = getItem(position).type
}
