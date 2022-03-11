package ru.inc.decideplusminus.presentation.ui.simple

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewModel
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState

class SimpleMainPageFragment :
    BaseFragment<FragmentSimpleBinding, SimpleMainPageViewState>(FragmentSimpleBinding::inflate) {

    //TODO подрубить tooltip для обучения пользователя

    private var adapter: SimpleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel<SimpleMainPageViewModel>()
        initListeners()
    }

    private fun initListeners() {
        //TODO если голосовое управление, показывать всплывающую подсказку в начале списка
        binding.createNewSolution.setOnClickListener {
            SimpleMainPageFragmentDirections.actionToBottomSheetCreateSimple().let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun initRecyclerView() {
        val listeners = object : SimpleAdapterListener.SimpleListener {
            override fun clickAddArgument(): (SimpleVO) -> Unit = {
                // TODO передавать еще имя кликнутого решения
                SimpleMainPageFragmentDirections.actionToBottomSheetInsertSolutionToSimpleDetails(it.id)
                    .let { action ->
                        findNavController().navigate(action)
                    }
            }

            override fun clickOpenDetailsArguments(): (SimpleVO) -> Unit = { simpleVO ->
                SimpleMainPageFragmentDirections.actionToSimpleDetails(simpleVO.name).let { action ->
                    action.parentId = simpleVO.id
                    findNavController().navigate(action)
                }
            }
        }

        adapter = SimpleAdapter(listeners)
        binding.recycler.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

    override fun renderState(state: SimpleMainPageViewState) {
        when (state) {
            is SimpleMainPageViewState.Success -> {
                adapter?.submitList(state.list)
            }
        }
    }
}