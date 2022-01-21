package ru.inc.decideplusminus.view_model.simple.details

import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

sealed class SimpleDetailsViewState {

    data class SuccessLists(val positiveList: List<BaseSimpleItem>?, val negativeList: List<BaseSimpleItem>?) :
        SimpleDetailsViewState()
}
