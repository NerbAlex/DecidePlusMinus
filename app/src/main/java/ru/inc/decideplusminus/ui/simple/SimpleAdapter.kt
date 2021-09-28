package ru.inc.decideplusminus.ui.simple

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.inc.decideplusminus.databinding.ItemSimpleSolutionNegativeBinding
import ru.inc.decideplusminus.databinding.ItemSimpleSolutionPositiveBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimpleAdapter(private val listeners: SimpleAdapterListeners) :
    ListAdapter<BaseSimpleSolutionItem, BaseViewHolder<BaseSimpleSolutionItem>>(SimpleSolutionDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseSimpleSolutionItem> =
        LayoutInflater.from(parent.context).let { inflater ->
            return when (viewType) {
                BaseSimpleSolutionItem.POSITIVE -> SimpleSolutionPositiveVH(
                    ItemSimpleSolutionPositiveBinding.inflate(inflater, parent, false)
                )
                BaseSimpleSolutionItem.NEGATIVE -> SimpleSolutionNegativeVH(
                    ItemSimpleSolutionNegativeBinding.inflate(inflater, parent, false)
                )
                else -> throw IllegalArgumentException("Unknown viewType")
            }
        }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseSimpleSolutionItem>, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int) = getItem(position).type


}