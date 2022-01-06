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

    //TODO при клике добавить вложенный +/- показывать заголовок решения во всплывающем bottomSheet

    //TODO посмотреть в ресайклер сбера, если  там скролится к добавленному итему, сделать так же с новым решением

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
        val listeners = object : SimpleAdapterListeners {
            override fun clickAddArgument(): (SimpleVO) -> Unit = {
                // TODO передавать SimpleVo в BottomSheetFragment
                SimpleFragmentDirections.actionNavigationHomeToBottomSheetAddInnerSolution().let { action ->
                    findNavController().navigate(action)
                }
            }

            override fun clickOpenDetailsArguments(): (SimpleVO) -> Unit = {
                SimpleFragmentDirections.actionNavigationHomeToSimpleSolutionFragment(it.name).let { action ->
                    findNavController().navigate(action)
                }

                //TODO переходим на страницу создания аргумента(можно здесь дернуть паблишсабджект, а в другом фрагменте получить,
                //таким образом данные будут собираться в одном потоке, а создание фрагмента в другом параллельно - оптимизация )
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

    override fun onResume() {
        super.onResume()
        val a = 10
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }

}