package ru.inc.decideplusminus.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.inc.decideplusminus.databinding.ItemSimpleNegativeVhBinding
import ru.inc.decideplusminus.databinding.ItemSimplePositiveVhBinding
import ru.inc.decideplusminus.databinding.ItemTeacherSolutionBinding
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleAdapterListeners
import ru.inc.decideplusminus.ui.simple.SimpleSolutionNegativeVH
import ru.inc.decideplusminus.ui.simple.SimpleSolutionPositiveVH

object ViewHoldersFactory {
    fun create(viewType: Int, parent: ViewGroup, listeners: SimpleAdapterListeners): BaseViewHolder<BaseSimpleItem> =
        LayoutInflater.from(parent.context).let { inflater ->
            return when (viewType) {
                BaseSimpleItem.POSITIVE -> SimpleSolutionPositiveVH(
                    ItemSimplePositiveVhBinding.inflate(inflater, parent, false), listeners
                )
                BaseSimpleItem.NEGATIVE -> SimpleSolutionNegativeVH(
                    ItemSimpleNegativeVhBinding.inflate(inflater, parent, false), listeners
                )
                BaseSimpleItem.TEACHER -> TeacherVH(
                    ItemTeacherSolutionBinding.inflate(inflater, parent, false)
                )
                else -> throw IllegalArgumentException("Unknown viewType")
            }
        }
}