package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.base.BaseFragment
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.view_model.simple.SimpleViewState

class SimpleFragment : BaseFragment<FragmentSimpleBinding>(FragmentSimpleBinding::inflate) {

    //TODO подрубить tooltip для обучения пользователя

    lateinit var viewModel: SimpleViewModel
    private var adapter: SimpleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        initListeners()
    }

    private fun initListeners() {
        //TODO если голосовое управление, показывать всплывающую подсказку в начале списка
        binding.createNewSolution.setOnClickListener {
            SimpleFragmentDirections.actionNavigationHomeToBottomSheetAddSimpleSolution().let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.downloadData()
    }

    private fun initRecyclerView() {
        val listeners = object : SimpleAdapterListener.SimpleListener {
            override fun clickAddArgument(): (SimpleVO) -> Unit = {
                // TODO передавать еще имя кликнутого решения
                SimpleFragmentDirections.actionNavigationHomeToBottomSheetAddInnerSolution(it.id).let { action ->
                    findNavController().navigate(action)
                }
            }

            override fun clickOpenDetailsArguments(): (SimpleVO) -> Unit = {
                SimpleFragmentDirections.actionNavigationHomeToSimpleSolutionFragment(it.name).let { action ->
                    action.parentId = it.id
                    findNavController().navigate(action)
                }
            }
        }

        adapter = SimpleAdapter(listeners)
        binding.recycler.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter
    }

    private fun renderData(stateSimple: SimpleViewState) = when (stateSimple) {
        is SimpleViewState.Success -> {
            adapter?.submitList(stateSimple.list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}