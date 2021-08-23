package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.base.BaseViewModel
import ru.inc.decideplusminus.ui.models.SimpleDecide

class SimpleViewModel : BaseViewModel<SimpleViewState>() {

    private val list = listOf<SimpleDecide>()

    fun start() {
        mutableLiveData.value = SimpleViewState.Success(list)
    }
}