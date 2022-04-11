package ru.inc.decideplusminus.presentation.view_model.simple.simple_details

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem

sealed class SimpleDetailsViewState {

    data class SuccessLists(val positiveList: List<BaseSimpleItem>?, val negativeList: List<BaseSimpleItem>?) :
        SimpleDetailsViewState()
}
