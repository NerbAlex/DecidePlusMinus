package ru.inc.decideplusminus.frameworks.base.base_presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import ru.inc.decideplusminus.presentation.ui.MyApp
import javax.inject.Inject

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<Binding : ViewBinding, State>(private val inflate: Inflate<Binding>) :
    BaseDaggerFragment() {
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

    protected fun updateView(block: Binding.() -> Unit) = block.invoke(binding)

    protected inline fun <reified VM : BaseViewModel<State>>initViewModel(): VM {
        val viewModel = viewModels<VM> { viewModelFactory }.value
        viewModel.getData().observe(viewLifecycleOwner, ::renderState)
        return viewModel
    }

    abstract fun renderState(state: State)
}

abstract class BaseDaggerFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}