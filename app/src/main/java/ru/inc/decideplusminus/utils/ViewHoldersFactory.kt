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
                    SimplePositiveVH(
                        ItemSimplePositiveVhBinding.inflate(inflater, parent, false), listener as SimpleListener
                    )
                }
                BaseSimpleItem.NEGATIVE -> {
                    SimpleNegativeVH(
                        ItemSimpleNegativeVhBinding.inflate(inflater, parent, false), listener as SimpleListener
                    )
                }
                BaseSimpleItem.TEACHER -> TeacherVH(
                    ItemTeacherSolutionBinding.inflate(inflater, parent, false)
                )
                BaseSimpleItem.NEUTRAL -> {
                    SimpleNeutralVH(
                        ItemSimpleNeutraleVhBinding.inflate(inflater, parent, false), listener as SimpleListener
                    )
                }
                BaseSimpleItem.DETAILS_NEGATIVE -> {
                    SimpleDetailsNegativeVH(
                        ItemSimpleDetailsNegativeVhBinding.inflate(inflater, parent, false), listener as SimpleDetailsListener
                    )
                }
                BaseSimpleItem.DETAILS_POSITIVE -> {
                    SimpleDetailsPositiveVH(
                        ItemSimpleDetailsPositiveVhBinding.inflate(inflater, parent, false), listener as SimpleDetailsListener
                    )
                }
                else -> throw IllegalArgumentException("Unknown viewType")
            }
        }
}