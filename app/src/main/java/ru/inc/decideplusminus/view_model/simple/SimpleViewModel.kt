package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.base.BaseViewModel
import javax.inject.Inject

class SimpleViewModel @Inject constructor(private val repository: SolutionRepository<SimpleViewState>) :
    BaseViewModel<SimpleViewState>() {


    fun start() {
        TODO()
    }
}