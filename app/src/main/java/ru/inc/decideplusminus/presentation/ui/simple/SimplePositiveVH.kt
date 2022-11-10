package ru.inc.decideplusminus.presentation.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimplePositiveVhBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewHolder

class SimplePositiveVH(
    private val view: ItemSimplePositiveVhBinding,
    private val listeners: SimpleListener
) :
    BaseViewHolder<BaseSimpleItem>(view) {
    override fun bind(model: BaseSimpleItem) = with(model as SimpleVO) {

        view.nameSolutionTv.text = name
        view.percentTv.text = percent

        view.openSolutionBtn.setOnClickListener {
            listeners.clickOpenDetailsArguments.invoke(model)
        }

        view.addArgumentBtn.setOnClickListener {
            listeners.clickAddArgument.invoke(model)
        }

        view.deleteArgumentBtn.setOnClickListener {
            listeners.clickADeleteArgument.invoke(model)
        }
    }
}