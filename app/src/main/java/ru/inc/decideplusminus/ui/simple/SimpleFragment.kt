package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.base.Item
import ru.inc.decideplusminus.ui.view_model.simple.SimpleViewModel
import ru.inc.decideplusminus.ui.view_model.simple.ViewState

class SimpleFragment : Fragment() {

    private lateinit var viewModel: SimpleViewModel
    private var _binding: FragmentSimpleBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentSimpleBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun renderData(state: ViewState) = when (state) {
        is ViewState.Success<*> -> {
            val list: List<Item> = state.list
        }

        else -> {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}