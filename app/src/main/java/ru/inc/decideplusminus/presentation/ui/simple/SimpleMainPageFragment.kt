package ru.inc.decideplusminus.presentation.ui.simple

import android.content.Context
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val a  = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a  = 10
    }


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
//                    binding.recycler.startLayoutAnimation()
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

    private fun clickAddArgument(vo: SimpleVO) {
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

    private fun clickDeleteArgument(vo: SimpleVO) {
        viewModel?.delete(vo.id)
    }

    private fun initRecyclerView() = with(binding.recycler) {
        val listeners = SimpleListener(::clickAddArgument, ::clickOpenDetailsArguments, ::clickDeleteArgument)

        simpleAdapter = SimpleAdapter(listeners)
        layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
//        setHasFixedSize(true) // todo когда выровняю все итемы по размеру
        adapter = simpleAdapter

        toolbarAnimationWithRecyclerView(this)
        layoutAnimation = createDefaultRecyclerAnimation()
    }
}

fun main() {
    val dtoFirst = Person1("Jack")
    val dtoSecond = Person2(18)

    val personVo1 = dtoFirst zipToPersonVo dtoSecond // todo прикольно
    val personVo2 = dtoFirst.zipToPersonVo(dtoSecond)

    println(personVo1)
}

private infix fun Person1.zipToPersonVo(some2: Person2): PersonVo {
    return PersonVo(name, some2.age)
}

data class Person1(val name:String)
data class Person2(val age: Int)
data class PersonVo(val name: String, val age: Int)
