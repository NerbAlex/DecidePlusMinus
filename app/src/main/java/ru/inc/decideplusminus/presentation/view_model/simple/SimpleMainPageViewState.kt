package ru.inc.decideplusminus.presentation.view_model.simple

import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem

sealed class SimpleMainPageViewState {

    data class Success(val list: List<BaseSimpleItem>): SimpleMainPageViewState()

}
