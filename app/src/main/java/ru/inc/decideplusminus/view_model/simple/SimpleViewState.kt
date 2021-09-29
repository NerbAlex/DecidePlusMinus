package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem

sealed class SimpleViewState {

    data class Success(val list: List<BaseSimpleSolutionItem>): SimpleViewState()

}
