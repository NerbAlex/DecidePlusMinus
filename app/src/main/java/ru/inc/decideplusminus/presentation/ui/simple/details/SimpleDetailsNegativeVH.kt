package ru.inc.decideplusminus.presentation.ui.simple.details

import ru.inc.decideplusminus.databinding.ItemSimpleDetailsNegativeVhBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewHolder
import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapterListener

class SimpleDetailsNegativeVH(
    private val binding: ItemSimpleDetailsNegativeVhBinding,
    private val listeners: SimpleAdapterListener.SimpleDetailsListener
) : BaseViewHolder<BaseSimpleItem>(binding) {
    override fun bind(model: BaseSimpleItem): Unit = with(model as SimpleDetailsVO) {
        binding.nameSolutionTv.text = model.name

        binding.openSolutionBtn.setOnClickListener {
            listeners.click().invoke(model)
        }
    }
}