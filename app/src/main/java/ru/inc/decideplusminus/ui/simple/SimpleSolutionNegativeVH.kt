package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimpleSolutionNegativeBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimpleSolutionNegativeVH(private val view: ItemSimpleSolutionNegativeBinding) :
    BaseViewHolder<BaseSimpleSolutionItem>(view) {
    override fun bind(model: BaseSimpleSolutionItem) {
        val simpleSolutionModel = model as SimpleSolution

        view.nameSolutionTv.text = simpleSolutionModel.name
        view.percentTv.text = simpleSolutionModel.percent
    }
}