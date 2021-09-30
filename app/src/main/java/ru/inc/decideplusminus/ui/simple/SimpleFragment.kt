package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.MyApp
import ru.inc.decideplusminus.ui.simple.solution.SimpleSolutionFragment
import ru.inc.decideplusminus.ui.simple.solution.SimpleSolutionFragmentArgs
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import javax.inject.Inject

class SimpleFragment : Fragment() {

    //TODO Напоминание подливать во все ветки дев, а дев всегда обновлять свежими данными, если они нужны для других веток

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SimpleViewModel by lazy { viewModel() }

    private var adapter: SimpleAdapter? = null
    private var _binding: FragmentSimpleBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentSimpleBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
    }


    private fun observeData() {
        MyApp.instance.appComponent.inject(this)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.start()
    }

    private fun viewModel() = ViewModelProvider(this, viewModelFactory).get(SimpleViewModel::class.java)

    private fun initRecyclerView() {
        val listeners = object : SimpleAdapterListeners {
            override fun clickAddArgument(): (SimpleSolution) -> Unit = {
                //TODO вызываем шторку с добавление аргумента
            }

            override fun clickOpenDetailsArguments(): (SimpleSolution) -> Unit = {
                SimpleFragmentDirections.actionNavigationHomeToSimpleSolutionFragment(it.name).let { action ->
                    findNavController().navigate(action)
                }

                //TODO переходим на страницу создания аргумента(можно здесь дернуть паблишсабджект, а в другом фрагменте получить,
                //таким образом данные будут собираться в одном потоке, а создание фрагмента в другом параллельно - оптимизация )
            }
        }

        adapter = SimpleAdapter(listeners)
        b.recycler.layoutManager = LinearLayoutManager(b.root.context, LinearLayoutManager.VERTICAL, false)
        b.recycler.setHasFixedSize(true)
        b.recycler.adapter = adapter
    }

    private fun renderData(stateSimple: SimpleViewState) = when (stateSimple) {
        is SimpleViewState.Success -> {
            adapter?.submitList(stateSimple.list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }
}