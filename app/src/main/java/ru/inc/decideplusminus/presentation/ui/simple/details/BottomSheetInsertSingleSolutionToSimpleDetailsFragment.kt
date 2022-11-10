package ru.inc.decideplusminus.presentation.ui.simple.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetInsertSingleSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.ui.MyApp
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsVM
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsViewState

class BottomSheetInsertSingleSolutionToSimpleDetailsFragment :
    BaseBottomSheetFragment<FragmentBottomSheetInsertSingleSolutionBinding, InsertSolutionToSimpleDetailsViewState>(
        FragmentBottomSheetInsertSingleSolutionBinding::inflate
    ) {

    private var viewModel: InsertSolutionToSimpleDetailsVM? = null
    private val navArgs: BottomSheetInsertSingleSolutionToSimpleDetailsFragmentArgs by navArgs()
    private var parentId: Long? = null
    private var isPositive: Boolean? = null
    private var parentName: String? = null
    private var argumentLvl: Int = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        updateUi()
        viewModel = initViewModel()

        binding.sendButton.setOnClickListener {
            val text = binding.editText.text.trim().toString()
            if (text.isNotEmpty()) {
                if (isPositive == true) viewModel?.plus(parentId, argumentLvl, text) else viewModel?.minus(parentId, argumentLvl, text)
            }
        }
    }

    private fun updateUi() {
        binding.editText.hint = parentName

        isPositive?.let { isPositive ->
            binding.sendButton.setBackgroundColor(
                if (isPositive) resources.getColor(R.color.success_light) else resources.getColor(R.color.error_light)
            )
        }
    }

    private fun getArgs() = with(navArgs) {
        parentId = solutionId
        parentName = solutionName
        isPositive = solutionStatus
    }

    override fun renderState(state: InsertSolutionToSimpleDetailsViewState) {
        when (state) {
            InsertSolutionToSimpleDetailsViewState.CompletedAddDetails -> {
                findNavController().popBackStack()
                viewModel?.getData()
                sendEvent(UiEvent.ReloadMainPage, UiEvent.ReloadDetailsPage)
            }
        }
    }
}
