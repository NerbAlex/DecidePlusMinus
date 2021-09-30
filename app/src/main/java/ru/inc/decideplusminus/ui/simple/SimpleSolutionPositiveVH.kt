package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimpleSolutionPositiveBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimpleSolutionPositiveVH(private val view: ItemSimpleSolutionPositiveBinding, val listeners: SimpleAdapterListeners) :
    BaseViewHolder<BaseSimpleSolutionItem>(view) {
    override fun bind(model: BaseSimpleSolutionItem) {
        val simpleSolutionModel = model as SimpleSolution

        view.nameSolutionTv.text = simpleSolutionModel.name
        view.percentTv.text = simpleSolutionModel.percent

        view.root.setOnClickListener {
            listeners.clickOpenDetailsArguments().invoke(simpleSolutionModel)
        }
    }
}