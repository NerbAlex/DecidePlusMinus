package ru.inc.decideplusminus.ui.simple.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.ui.base.BaseFragment
import ru.inc.decideplusminus.ui.simple.SimpleAdapter
import ru.inc.decideplusminus.ui.simple.SimpleAdapterListener
import ru.inc.decideplusminus.view_model.simple.details.SimpleDetailsViewModel
import ru.inc.decideplusminus.view_model.simple.details.SimpleDetailsViewState

class SimpleDetailsFragment : BaseFragment<FragmentSimpleDetailsBinding>(FragmentSimpleDetailsBinding::inflate) {

    private val navArgs: SimpleDetailsFragmentArgs by navArgs()
    lateinit var viewModel: SimpleDetailsViewModel
    private var adapterPositive: SimpleAdapter? = null
    private var adapterNegative: SimpleAdapter? = null

    //TODO передавать сюда id решения, в репе хранить мапу ключ - parentId, значение - лист вложенных аргументов

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        initViewModel()
    }

    private fun listener(): SimpleAdapterListener.SimpleDetailsListener =
        object : SimpleAdapterListener.SimpleDetailsListener {
            override fun click(): (SimpleDetailsVO) -> Unit = {

            }
        }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SimpleDetailsViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        val id = navArgs.parentId
        viewModel.searchById(id)
    }

    private fun renderData(state: SimpleDetailsViewState) {
        when(state) {
            is SimpleDetailsViewState.SuccessLists -> {
                adapterPositive?.submitList(state.positiveList)
                adapterNegative?.submitList(state.negativeList)
            }
        }
    }

    private fun initRecyclerViews() {
        adapterPositive = SimpleAdapter(listener())
        binding.recyclerPositive.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerPositive.setHasFixedSize(true)
        binding.recyclerPositive.adapter = adapterPositive

        adapterNegative = SimpleAdapter(listener())
        binding.recyclerNegative.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerNegative.setHasFixedSize(true)
        binding.recyclerNegative.adapter = adapterNegative
    }
}