package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.inc.decideplusminus.databinding.FragmentBottomSheetCreateSimpleSolutionBinding
import ru.inc.decideplusminus.ui.base.BaseBottomSheetFragment
import ru.inc.decideplusminus.view_model.simple.CreateSimpleViewModel
import ru.inc.decideplusminus.view_model.simple.CreateSimpleViewState

class BottomSheetCreateSimpleSolution :
    BaseBottomSheetFragment<FragmentBottomSheetCreateSimpleSolutionBinding>(FragmentBottomSheetCreateSimpleSolutionBinding::inflate) {

    private lateinit var viewModel: CreateSimpleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        // TODO При дизайне закруглить диалог

        // TODO получать из аргументов NavigationAction и передавать его дальше во вьюмодель, та в какой то общий котел, который уже в потоке разруливает куда сохранять и т.п.

        binding.createSolution.setOnClickListener {
            val name = binding.editText.text.toString()
            viewModel.createSolution(name)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateSimpleViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            when(state) {
                CreateSimpleViewState.Created -> findNavController().popBackStack()
            }
        }
    }
}