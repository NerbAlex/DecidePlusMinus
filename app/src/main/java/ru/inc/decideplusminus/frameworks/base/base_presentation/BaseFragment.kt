package ru.inc.decideplusminus.frameworks.base.base_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import ru.inc.decideplusminus.utils.viewModel

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<Binding : ViewBinding, State>(private val inflate: Inflate<Binding>) :
    BaseDaggerFragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflate.invoke(inflater, container, false).also { _binding = it }.root

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
}
