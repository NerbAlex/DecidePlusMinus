package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimpleNegativeVhBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimpleSolutionNegativeVH(
    private val view: ItemSimpleNegativeVhBinding,
    private val listeners: SimpleAdapterListeners
) :
    BaseViewHolder<BaseSimpleItem>(view) {
    override fun bind(model: BaseSimpleItem) {
        val simpleSolutionModel = model as SimpleVO

        view.nameSolutionTv.text = simpleSolutionModel.name
        view.percentTv.text = simpleSolutionModel.percent

        view.openSolutionBtn.setOnClickListener {
            listeners.clickOpenDetailsArguments().invoke(simpleSolutionModel)
        }

        view.addArgumentBtn.setOnClickListener {
            listeners.clickAddArgument().invoke(simpleSolutionModel)
        }
    }
}