package ru.inc.decideplusminus.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetInsertSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsVM
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsViewState

class BottomSheetInsertSolutionToSimpleDetailsFragment :
    BaseBottomSheetFragment<FragmentBottomSheetInsertSolutionBinding, InsertSolutionToSimpleDetailsViewState>(
        FragmentBottomSheetInsertSolutionBinding::inflate
    ) {

    private var viewModel: InsertSolutionToSimpleDetailsVM? = null
    private val navArgs: BottomSheetInsertSolutionToSimpleDetailsFragmentArgs by navArgs()
    private var parentId: Long? = null
    private var argumentLvl: Int = 2

    var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        viewModel = initViewModel()

        // TODO При дизайне закруглить диалог

        // TODO скрывать клавиатуру, рассмотреть все кейсы с микрофоном и плюс/минусом

        updateView {
            mic
        }

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
            viewModel?.minus(parentId, argumentLvl, binding.editText.text.toString().trim())
        }

        binding.plus.setOnClickListener {
            viewModel?.plus(parentId, argumentLvl, binding.editText.text.toString().trim())
        }
    }

    private fun getArgs() {
        parentId = navArgs.solutionId
    }

    override fun renderState(state: InsertSolutionToSimpleDetailsViewState) {
        when (state) {
            InsertSolutionToSimpleDetailsViewState.CompletedAddDetails -> {
                findNavController().popBackStack()
//                sendEvent(UiEvent.ReloadMainPage)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        sendEvent(UiEvent.ReloadMainPage)
    }
}
