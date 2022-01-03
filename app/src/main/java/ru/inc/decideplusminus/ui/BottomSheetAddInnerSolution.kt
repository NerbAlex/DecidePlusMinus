package ru.inc.decideplusminus.ui

import android.os.Bundle
import android.view.View
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetAddInnerSolutionBinding
import ru.inc.decideplusminus.ui.base.BaseBottomSheetFragment

class BottomSheetAddInnerSolution :
    BaseBottomSheetFragment<FragmentBottomSheetAddInnerSolutionBinding>(FragmentBottomSheetAddInnerSolutionBinding::inflate) {

    var flag = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO При дизайне закруглить диалог

        // TODO скрывать клавиатуру, рассмотреть все кейсы с микрофоном и плюс/минусом

        binding.mic.setOnClickListener {
            if (flag) {
                flag = false
                binding.mic.setCardBackgroundColor(resources.getColor(R.color.error))
                binding.editText.visibility = View.INVISIBLE
                binding.editText.text.clear()
                // TODO вкл/выкл запись
            } else {
                flag = true
                binding.mic.setCardBackgroundColor(resources.getColor(R.color.success_light))
                binding.editText.visibility = View.VISIBLE
                binding.editText.setText("какой то записанный текст")
            }
        }

        binding.minus.setOnClickListener {

        }

        binding.plus.setOnClickListener {  }
    }
}