package ru.inc.decideplusminus.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified V : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory) =
    viewModels<V> { factory }.value