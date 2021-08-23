package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.view_model.simple.SimpleViewState

class SimpleFragment : Fragment() {

    private lateinit var viewModel: SimpleViewModel
    private var adapter: SimpleAdapter? = null
    private var _binding: FragmentSimpleBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentSimpleBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.start()
    }

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