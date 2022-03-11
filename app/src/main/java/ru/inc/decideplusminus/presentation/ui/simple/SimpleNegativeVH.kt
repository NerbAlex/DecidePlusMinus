package ru.inc.decideplusminus.presentation.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimpleNegativeVhBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewHolder

class SimpleNegativeVH(
    private val view: ItemSimpleNegativeVhBinding,
    private val listeners: SimpleAdapterListener.SimpleListener
) :
    BaseViewHolder<BaseSimpleItem>(view) {
    override fun bind(model: BaseSimpleItem) = with(model as SimpleVO) {

        view.nameSolutionTv.text = name
        view.percentTv.text = percent

        view.openSolutionBtn.setOnClickListener {
            listeners.clickOpenDetailsArguments().invoke(model)
        }

        view.addArgumentBtn.setOnClickListener {
            listeners.clickAddArgument().invoke(model)
        }
    }
}