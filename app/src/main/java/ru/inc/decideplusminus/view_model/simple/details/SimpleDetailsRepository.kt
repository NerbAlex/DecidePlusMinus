package ru.inc.decideplusminus.view_model.simple.details

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

interface SimpleDetailsRepository {
    fun searchSolutionById(id: Long): Single<SimpleDetailsViewState>
}