package ru.inc.decideplusminus.frameworks.base.base_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.inc.decideplusminus.presentation.ui.events.UiEvent
import ru.inc.decideplusminus.utils.viewModel

abstract class BaseBottomSheetFragment<Binding : ViewBinding, State>(private val inflate: Inflate<Binding>) :
    BaseDaggerBottomSheetFragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun renderState(state: State)

    protected fun updateView(block: Binding.() -> Unit) = block.invoke(binding)

    protected inline fun <reified VM : BaseViewModel<State>> initViewModel(): VM {
        val viewModel = viewModel<VM>(viewModelFactory)
        viewModel.getData().observe(viewLifecycleOwner, ::renderState)
        return viewModel
    }

    protected fun sendEvent(event: UiEvent) {
        with(uiEvents) {
            this@BaseBottomSheetFragment.requireContext().sendEvent(event)
        }
    }
}