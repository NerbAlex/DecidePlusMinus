package ru.inc.decideplusminus.ui.simple.solution

import android.os.Bundle
import android.view.View
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.ui.base.BaseFragment

class SimpleDetailsFragment : BaseFragment<FragmentSimpleDetailsBinding>(FragmentSimpleDetailsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {

    }
}