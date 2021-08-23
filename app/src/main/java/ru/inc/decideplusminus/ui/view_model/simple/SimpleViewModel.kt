package ru.inc.decideplusminus.ui.view_model.simple

import ru.inc.decideplusminus.ui.models.Decide
import ru.inc.decideplusminus.ui.view_model.BaseViewModel

class SimpleViewModel : BaseViewModel<ViewState>() {

    val list = listOf(Decide(0, ""))

        fun some() {
            mutableLiveData.value = ViewState.Success(list)
        }
}