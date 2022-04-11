package ru.inc.decideplusminus.presentation.view_model.simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem

sealed class SimpleMainPageViewState {

    data class Success(val list: List<BaseSimpleItem>): SimpleMainPageViewState()

}
