package ru.inc.decideplusminus.ui.simple.solution

import android.os.Bundle
import android.view.View
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.ui.base.BaseFragment

class SimpleDetailsFragment : BaseFragment<FragmentSimpleDetailsBinding>(FragmentSimpleDetailsBinding::inflate) {

    //TODO передавать сюда id решения, в репе хранить мапу ключ - parentId, значение - лист вложенных аргументов

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {

    }
}