package ru.inc.decideplusminus.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetInsertSolutionBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseBottomSheetFragment
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsVM
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsViewState
import ru.inc.decideplusminus.utils.viewModel
import javax.inject.Inject

class BottomSheetInsertSolutionToSimpleDetailsFragment :
    BaseBottomSheetFragment<FragmentBottomSheetInsertSolutionBinding>(FragmentBottomSheetInsertSolutionBinding::inflate) {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var detailsVM: InsertSolutionToSimpleDetailsVM
    private val navArgs: BottomSheetInsertSolutionToSimpleDetailsFragmentArgs by navArgs()
    private var solutionId: Long? = null
    private var argumentLvl: Int = 2

    var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
        initViewModel()

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
            detailsVM.minus(solutionId, argumentLvl, binding.editText.text.toString().trim())
        }

        binding.plus.setOnClickListener {
            detailsVM.plus(solutionId, argumentLvl, binding.editText.text.toString().trim())
        }
    }

    private fun getArgs() {
        solutionId = navArgs.solutionId
    }

    private fun initViewModel() {
        detailsVM = viewModel(viewModelProvider)
        detailsVM.getData().observe(viewLifecycleOwner) { state ->
            when (state) {
                InsertSolutionToSimpleDetailsViewState.CompletedAddDetails -> findNavController().popBackStack()
            }
        }
    }
}