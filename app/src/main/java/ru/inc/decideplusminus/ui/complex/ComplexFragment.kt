package ru.inc.decideplusminus.ui.complex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.inc.decideplusminus.databinding.FragmentComplexBinding

class ComplexFragment : Fragment() {

    private lateinit var complexViewModel: ComplexViewModel
    private var _binding: FragmentComplexBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        complexViewModel =
            ViewModelProvider(this).get(ComplexViewModel::class.java)

        _binding = FragmentComplexBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        complexViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}