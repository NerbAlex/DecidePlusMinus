package ru.inc.decideplusminus.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.inc.decideplusminus.databinding.*
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewHolder
import ru.inc.decideplusminus.presentation.ui.simple.teachers.TeacherVH
import ru.inc.decideplusminus.presentation.ui.simple.*
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsNegativeVH
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsPositiveVH

object ViewHoldersFactory {
    fun create(viewType: Int, parent: ViewGroup, listener: SimpleAdapterListener): BaseViewHolder<BaseSimpleItem> =
        LayoutInflater.from(parent.context).let { inflater ->
            return when (viewType) {
                BaseSimpleItem.POSITIVE -> {
                    val listeners = listener as SimpleAdapterListener.SimpleListener
                    SimplePositiveVH(
                        ItemSimplePositiveVhBinding.inflate(inflater, parent, false), listeners
                    )
                }
                BaseSimpleItem.NEGATIVE -> {
                    val listeners = listener as SimpleAdapterListener.SimpleListener
                    SimpleNegativeVH(
                        ItemSimpleNegativeVhBinding.inflate(inflater, parent, false), listeners
                    )
                }
                BaseSimpleItem.TEACHER -> TeacherVH(
                    ItemTeacherSolutionBinding.inflate(inflater, parent, false)
                )
                BaseSimpleItem.NEUTRAL -> {
                    val listeners = listener as SimpleAdapterListener.SimpleListener
                    SimpleNeutralVH(
                        ItemSimpleNeutraleVhBinding.inflate(inflater, parent, false), listeners
                    )
                }
                BaseSimpleItem.DETAILS_NEGATIVE -> {
                    val listeners = listener as SimpleAdapterListener.SimpleDetailsListener
                    SimpleDetailsNegativeVH(
                        ItemSimpleDetailsNegativeVhBinding.inflate(inflater, parent, false), listeners
                    )
                }
                BaseSimpleItem.DETAILS_POSITIVE -> {
                    val listeners = listener as SimpleAdapterListener.SimpleDetailsListener
                    SimpleDetailsPositiveVH(
                        ItemSimpleDetailsPositiveVhBinding.inflate(inflater, parent, false), listeners
                    )
                }
                else -> throw IllegalArgumentException("Unknown viewType")
            }
        }
}