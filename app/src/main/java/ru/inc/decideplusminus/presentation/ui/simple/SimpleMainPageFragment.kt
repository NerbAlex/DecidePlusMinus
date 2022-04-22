package ru.inc.decideplusminus.presentation.ui.simple

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewModel
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState
import ru.inc.decideplusminus.utils.extensions.scrollOrSmoothScrollToPosition

class SimpleMainPageFragment :
    BaseFragment<FragmentSimpleBinding, SimpleMainPageViewState>(FragmentSimpleBinding::inflate) {

    //TODO подрубить tooltip для обучения пользователя

    // TODO список должен пружинить, что бы не мешал FAB и можно было прочитать нижний item

    // TODO при добавлении в список нового итема, он должен скролится к последнему

    private var adapter: SimpleAdapter? = null
    private var viewModel: SimpleMainPageViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = initViewModel()
        initListeners()

        viewModel?.downloadData()
        eventHandler {
            if (it is UiEvent.ReloadMainPage) viewModel?.downloadData()
        }
    }

    private fun bindLastItem(lastPosition: Int) {
        updateView {
            recycler.stopScroll()
            adapter?.let {
                recycler.scrollOrSmoothScrollToPosition(lastPosition)
            }
        }
    }

    override fun renderState(state: SimpleMainPageViewState) {
        when (state) {
            is SimpleMainPageViewState.Success -> {
                adapter?.submitList(state.list) {
                    if (state.list.isEmpty()) return@submitList
                    bindLastItem(state.list.lastIndex)
                }
                // TODO сделать листенер на последний забинденный item
//                Thread.sleep(1000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
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

        adapter = SimpleAdapter(listeners, ::bindLastItem)
        binding.recycler.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter
    }
}