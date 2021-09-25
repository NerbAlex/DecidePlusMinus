package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.MyApp
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import javax.inject.Inject

class SimpleFragment : Fragment() {

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
        adapter = SimpleAdapter()
        b.recycler.layoutManager = LinearLayoutManager(b.root.context, LinearLayoutManager.VERTICAL, false)
        b.recycler.setHasFixedSize(true)
        b.recycler.adapter = adapter
    }

    private fun renderData(stateSimple: SimpleViewState) = when (stateSimple) {
        is SimpleViewState.Success -> {
            adapter?.list = stateSimple.list
        }
        SimpleViewState.Error -> TODO()
        SimpleViewState.Loading -> TODO()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}