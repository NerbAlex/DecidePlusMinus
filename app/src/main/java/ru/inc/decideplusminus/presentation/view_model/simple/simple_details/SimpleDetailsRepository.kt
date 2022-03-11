package ru.inc.decideplusminus.presentation.view_model.simple.simple_details

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState

interface SimpleDetailsRepository {
    fun searchSolutionById(id: Long): Single<SimpleDetailsViewState>
}