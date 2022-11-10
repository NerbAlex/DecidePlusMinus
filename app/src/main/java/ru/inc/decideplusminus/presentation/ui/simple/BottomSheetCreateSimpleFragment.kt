package ru.inc.decideplusminus.presentation.ui.simple

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetCreateSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSimpleVM
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSimpleViewState

class BottomSheetCreateSimpleFragment :
    BaseBottomSheetFragment<FragmentBottomSheetCreateSolutionBinding, CreateSimpleViewState>(
        FragmentBottomSheetCreateSolutionBinding::inflate
    ) {

    private var viewModel: CreateSimpleVM? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = initViewModel()

        // TODO При дизайне закруглить диалог

        // TODO получать из аргументов NavigationAction и передавать его дальше во вьюмодель, та в какой то общий котел, который уже в потоке разруливает куда сохранять и т.п.

        binding.createSolution.setOnClickListener {
            val name = binding.editText.text.toString()
            viewModel?.createSolution(name)
        }
    }

    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

    override fun renderState(state: CreateSimpleViewState) {
        when (state) {
            CreateSimpleViewState.Created -> {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        sendEvent(UiEvent.ReloadMainPage)
    }
}