package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.databinding.ItemSimplePositiveVhBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder

class SimplePositiveVH(
    private val view: ItemSimplePositiveVhBinding,
    private val listeners: SimpleAdapterListener.SimpleListener
) :
    BaseViewHolder<BaseSimpleItem>(view) {
    override fun bind(model: BaseSimpleItem) = with(model as SimpleVO) {

        view.nameSolutionTv.text = name
        view.percentTv.text = percent

        view.openSolutionBtn.setOnClickListener {
            listeners.clickOpenDetailsArguments().invoke(this)
        }

        view.addArgumentBtn.setOnClickListener {
            listeners.clickAddArgument().invoke(this)
        }
    }
}