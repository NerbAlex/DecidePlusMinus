package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.models.SimpleDecide

sealed class SimpleViewState {

    data class Success(val list: List<SimpleDecide>): SimpleViewState()
    object Loading: SimpleViewState()
    object Error: SimpleViewState()
}
