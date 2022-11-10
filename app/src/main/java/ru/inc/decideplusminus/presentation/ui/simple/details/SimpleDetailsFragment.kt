package ru.inc.decideplusminus.presentation.ui.simple.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapter
import ru.inc.decideplusminus.presentation.ui.simple.SimpleDetailsListener
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewModel

class SimpleDetailsFragment :
    BaseFragment<FragmentSimpleDetailsBinding, SimpleDetailsViewState>(FragmentSimpleDetailsBinding::inflate) {

    private val navArgs: SimpleDetailsFragmentArgs by navArgs()
    private val viewModel: SimpleDetailsViewModel by lazy { initViewModel() }
    private var adapterPositive: SimpleAdapter? = null
    private var adapterNegative: SimpleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initListeners()
        viewModel.searchById(navArgs.parentId)

        eventHandler(UiEvent.ReloadDetailsPage) {
            viewModel.searchById(navArgs.parentId)
        }
    }

    private fun initListeners() = with(navArgs) {
        updateView {
            createNegative.setOnClickListener {
                SimpleDetailsFragmentDirections.actionSimpleDetailsFragmentToBottomSheetInsertSingleSolutionToSimpleDetailsFragment(
                    parentId, toolbarName, false
                ).let { action -> findNavController().navigate(action) }
            }
            createPositive.setOnClickListener {
                SimpleDetailsFragmentDirections.actionSimpleDetailsFragmentToBottomSheetInsertSingleSolutionToSimpleDetailsFragment(
                    parentId, toolbarName, true
                ).let { action -> findNavController().navigate(action) }
            }
        }
    }

//    override fun onDetach() {
//        super.onDetach()
//        sendEvent(UiEvent.ReloadMainPage)
//    }

    private fun onClick(vo: SimpleDetailsVO) {

    }

    private fun onDelete(vo: SimpleDetailsVO) {
        viewModel.delete(vo)
    }

    private fun bindLastItem(lastPosition: Int) {

    }

    private fun initRecyclerViews() {
        adapterPositive = SimpleAdapter(SimpleDetailsListener(::onClick, ::onDelete))
        binding.recyclerPositive.setHasFixedSize(true) // todo удалить, когда сделаю раскрываемые айтемы с подробным описанием
        binding.recyclerPositive.adapter = adapterPositive

        adapterNegative = SimpleAdapter(SimpleDetailsListener(::onClick, ::onDelete))
        binding.recyclerNegative.setHasFixedSize(true)
        binding.recyclerNegative.adapter = adapterNegative
    }

    override fun renderState(state: SimpleDetailsViewState) {
        when (state) {
            is SimpleDetailsViewState.SuccessLists -> {
                adapterPositive?.submitList(state.positiveList)
                adapterNegative?.submitList(state.negativeList)
            }
        }
    }
}
