package ru.inc.decideplusminus.ui.simple.details

import ru.inc.decideplusminus.databinding.ItemSimpleDetailsNegativeVhBinding
import ru.inc.decideplusminus.ui.base.BaseViewHolder
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleAdapterListener

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