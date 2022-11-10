package ru.inc.decideplusminus.presentation.view_model.simple.simple_details

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO

interface SimpleDetailsUseCase {
    fun deleteSimpleDetail(vo: SimpleDetailsVO) : Completable
    fun getSimpleDetailsSolutions(parentId: Long) : Single<Pair<List<SimpleDetailsVO>, List<SimpleDetailsVO>>>
}