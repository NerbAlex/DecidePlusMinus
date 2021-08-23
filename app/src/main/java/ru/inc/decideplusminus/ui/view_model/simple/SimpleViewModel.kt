package ru.inc.decideplusminus.ui.view_model.simple

import ru.inc.decideplusminus.ui.base.BaseViewModel
import ru.inc.decideplusminus.ui.models.SimpleDecide

class SimpleViewModel : BaseViewModel<ViewState>() {

    val list = listOf<SimpleDecide>()

    fun  some() {
        mutableLiveData.value = ViewState.Success(list)
    }


}