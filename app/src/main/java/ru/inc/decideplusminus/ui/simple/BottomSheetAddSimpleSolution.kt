package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.View
import ru.inc.decideplusminus.databinding.FragmentBottomSheetAddSimpleSolutionBinding
import ru.inc.decideplusminus.ui.base.BaseBottomSheetFragment

class BottomSheetAddSimpleSolution :
    BaseBottomSheetFragment<FragmentBottomSheetAddSimpleSolutionBinding>(FragmentBottomSheetAddSimpleSolutionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO При дизайне закруглить диалог

        // TODO получать из аргументов NavigationAction и передавать его дальше во вьюмодель, та в какой то общий котел, который уже в потоке разруливает куда сохранять и т.п.

        binding.createSolution.setOnClickListener {

        }
    }
}