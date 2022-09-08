package ru.inc.decideplusminus.presentation.ui.simple

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewModel
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState
import ru.inc.decideplusminus.utils.extensions.createDefaultRecyclerAnimation
import ru.inc.decideplusminus.utils.extensions.scrollOrSmoothScrollToPosition
import ru.inc.decideplusminus.utils.extensions.toolbarAnimationWithRecyclerView

class SimpleMainPageFragment :
    BaseFragment<FragmentSimpleBinding, SimpleMainPageViewState>(FragmentSimpleBinding::inflate) {

    //TODO подрубить tooltip для обучения пользователя

    // TODO список должен пружинить, что бы не мешал FAB и можно было прочитать нижний item

    // TODO при добавлении в список нового итема, он должен скролится к последнему

    private var simpleAdapter: SimpleAdapter? = null
    private var viewModel: SimpleMainPageViewModel? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel = initViewModel()
        initListeners()
        viewModel?.downloadData()

        eventHandler(UiEvent.ReloadMainPage) {
            viewModel?.downloadData()
        }
    }

    private fun bindLastItem(lastPosition: Int) {
        updateView {
            recycler.stopScroll()
            simpleAdapter?.let {
                recycler.scrollOrSmoothScrollToPosition(lastPosition)
            }
        }
    }

    override fun renderState(state: SimpleMainPageViewState) {
        when (state) {
            is SimpleMainPageViewState.Success -> {
                simpleAdapter?.submitList(state.list) {
                    binding.recycler.startLayoutAnimation()
//                    if (state.list.isEmpty()) return@submitList
//                    bindLastItem(state.list.lastIndex)
                }
                // TODO сделать листенер на последний забинденный item
//                Thread.sleep(1000)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        simpleAdapter = null
    }

    private fun initListeners() {
        //TODO если голосовое управление, показывать всплывающую подсказку в начале списка
        binding.createNewSolution.setOnClickListener {
            SimpleMainPageFragmentDirections.actionToBottomSheetCreateSimple().let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun clickArgument(vo: SimpleVO) {
        // TODO передавать еще имя кликнутого решения
        SimpleMainPageFragmentDirections.actionToBottomSheetInsertSolutionToSimpleDetails(vo.id, vo.name)
            .let { action ->
                findNavController().navigate(action)
            }
    }

    private fun clickOpenDetailsArguments(vo: SimpleVO) {
        SimpleMainPageFragmentDirections.actionToSimpleDetails(vo.name).let { action ->
            action.parentId = vo.id
            findNavController().navigate(action)
        }
    }

    private fun initRecyclerView() = with(binding.recycler) {
        val listeners = SimpleListener(::clickArgument, ::clickOpenDetailsArguments)

        simpleAdapter = SimpleAdapter(listeners)
        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
//        setHasFixedSize(true) // todo когда выровняю все итемы по размеру
        adapter = simpleAdapter

        toolbarAnimationWithRecyclerView(this)
        layoutAnimation = createDefaultRecyclerAnimation()
    }
}