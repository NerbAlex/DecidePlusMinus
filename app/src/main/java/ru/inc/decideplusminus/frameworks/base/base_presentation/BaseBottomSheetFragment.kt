package ru.inc.decideplusminus.frameworks.base.base_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<Binding : ViewBinding>(private val inflate: Inflate<Binding>) :
    BottomSheetDialogFragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    protected fun updateView(block: Binding.() -> Unit) = block.invoke(binding)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}