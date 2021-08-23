package ru.inc.decideplusminus.ui.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding
import ru.inc.decideplusminus.ui.view_model.simple.SimpleViewModel

class SimpleFragment : Fragment() {

    private lateinit var simpleViewModel: SimpleViewModel
    private var _binding: FragmentSimpleBinding? = null
    private val b get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ) =
        FragmentSimpleBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        simpleViewModel = ViewModelProvider(this).get(SimpleViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}