package ru.inc.decideplusminus.presentation.ui.simple.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapter
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapterListener
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

        val id = navArgs.parentId
        viewModel.searchById(id)
    }

    private fun listener(): SimpleAdapterListener.SimpleDetailsListener =
        object : SimpleAdapterListener.SimpleDetailsListener {
            override fun click(): (SimpleDetailsVO) -> Unit = {
                // TODO поменять это на красивые лямбды с сылками на метод
            }
        }

    private fun initRecyclerViews() {
        adapterPositive = SimpleAdapter(listener())
        binding.recyclerPositive.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerPositive.setHasFixedSize(true)
        binding.recyclerPositive.adapter = adapterPositive

        adapterNegative = SimpleAdapter(listener())
        binding.recyclerNegative.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
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
