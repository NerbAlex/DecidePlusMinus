package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.base.BaseViewModel
import ru.inc.decideplusminus.ui.models.SimpleSolution

class SimpleViewModel : BaseViewModel<SimpleViewState>() {

    private val list = listOf<SimpleSolution>()

    fun start() {
        mutableLiveData.value = SimpleViewState.Success(list)
    }
}