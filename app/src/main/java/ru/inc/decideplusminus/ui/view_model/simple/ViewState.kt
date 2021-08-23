package ru.inc.decideplusminus.ui.view_model.simple

import ru.inc.decideplusminus.ui.base.Item

sealed class ViewState {
    data class Success<I: Item>(val list: List<I>): ViewState()
    object Loading: ViewState()
    object Error: ViewState()
}
