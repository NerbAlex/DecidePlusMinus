package ru.inc.decideplusminus.presentation.ui.simple

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.inc.decideplusminus.databinding.FragmentBottomSheetCreateSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.ui.MyApp
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSimpleVM
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSimpleViewState
import ru.inc.decideplusminus.utils.viewModel
import javax.inject.Inject

class BottomSheetCreateSimpleFragment :
    BaseBottomSheetFragment<FragmentBottomSheetCreateSolutionBinding>(
        FragmentBottomSheetCreateSolutionBinding::inflate
    ) {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var viewModel: CreateSimpleVM

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

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
        viewModel = viewModel(viewModelProvider)
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            when (state) {
                CreateSimpleViewState.Created -> findNavController().popBackStack()
            }
        }
    }
}