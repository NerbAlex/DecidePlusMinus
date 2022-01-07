package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimpleNeutraleVhBinding
import ru.inc.decideplusminus.databinding.ItemSimplePositiveVhBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimpleNeutralVH(
    private val view: ItemSimpleNeutraleVhBinding,
    private val listeners: SimpleAdapterListeners
) :
    BaseViewHolder<BaseSimpleItem>(view) {
    override fun bind(model: BaseSimpleItem) {
        val simpleSolutionModel = model as SimpleVO

        view.nameSolutionTv.text = simpleSolutionModel.name
        view.percent.text = simpleSolutionModel.percent

        view.openSolutionBtn.setOnClickListener {
            listeners.clickOpenDetailsArguments().invoke(simpleSolutionModel)
        }

        view.addArgumentBtn.setOnClickListener {
            listeners.clickAddArgument().invoke(simpleSolutionModel)
        }
    }
}