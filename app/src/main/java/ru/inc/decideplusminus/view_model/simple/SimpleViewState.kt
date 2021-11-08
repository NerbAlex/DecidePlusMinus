package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

sealed class SimpleViewState {

    data class Success(val list: List<BaseSimpleItem>): SimpleViewState()

}
