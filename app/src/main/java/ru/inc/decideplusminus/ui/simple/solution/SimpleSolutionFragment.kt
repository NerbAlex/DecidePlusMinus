package ru.inc.decideplusminus.ui.simple.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.inc.decideplusminus.databinding.FragmentSimpleBinding


class SimpleSolutionFragment : Fragment() {

    private var _binding: FragmentSimpleBinding? = null
    private val b get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentSimpleBinding.inflate(inflater, container, false).also { _binding = it }.root
}