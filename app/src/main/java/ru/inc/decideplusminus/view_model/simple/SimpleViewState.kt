package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.ui.simple.SimpleSolution

sealed class SimpleViewState {

    data class Success(val list: List<SimpleSolution>): SimpleViewState()

}
