package ru.inc.decideplusminus.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.databinding.FragmentBottomSheetAddInnerSolutionBinding
import ru.inc.decideplusminus.ui.base.BaseBottomSheetFragment
import ru.inc.decideplusminus.view_model.simple.AddInnerSimpleViewModel
import ru.inc.decideplusminus.view_model.simple.AddInnerSimpleViewState

class BottomSheetAddInnerSolution :
    BaseBottomSheetFragment<FragmentBottomSheetAddInnerSolutionBinding>(FragmentBottomSheetAddInnerSolutionBinding::inflate) {

    private lateinit var viewModel: AddInnerSimpleViewModel
    private val navArgs: BottomSheetAddInnerSolutionArgs by navArgs()
    private var solutionId: Long? = null
    private var argumentLvl: Int = 2


    var flag = true

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
            viewModel.minus(solutionId, argumentLvl, binding.editText.text.toString().trim())
        }

        binding.plus.setOnClickListener {
            viewModel.plus(solutionId, argumentLvl, binding.editText.text.toString().trim())
        }
    }

    private fun getArgs() {
        solutionId = navArgs.solutionId
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(AddInnerSimpleViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { state ->
            when (state) {
                AddInnerSimpleViewState.CompletedAdd -> findNavController().popBackStack()
            }
        }
    }
}