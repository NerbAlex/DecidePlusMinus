package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.MyApp
import ru.inc.decideplusminus.ui.base.BaseFragment
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import javax.inject.Inject

class SimpleFragment : BaseFragment<FragmentSimpleBinding>(FragmentSimpleBinding::inflate) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SimpleViewModel by lazy { viewModel() }

    private var adapter: SimpleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        initListeners()
    }

    private fun initListeners() {
        binding.createNewSolution.setOnClickListener {
            SimpleFragmentDirections.actionNavigationHomeToBottomSheetAddSimpleSolution().let { action ->
                findNavController().navigate(action)
            }
        }
    }

    private fun observeData() {
        MyApp.instance.appComponent.inject(this)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.start()
    }

    private fun viewModel() = ViewModelProvider(this, viewModelFactory).get(SimpleViewModel::class.java)

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

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}