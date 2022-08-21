package ru.inc.decideplusminus.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState

interface SimpleLocalDataSource {

    fun getAll(): Single<List<BaseSimpleItem>>
    fun insert(simpleSolution: SimpleVO): Completable
    fun create(simpleSolution: SimpleVO): Completable
    fun getDetailsById(parentId: Long): Single<Pair<List<SimpleDetailsVO>, List<SimpleDetailsVO>>>
    fun insertDetails(detailsVO: SimpleDetailsVO): Completable
    fun getSimple(id: Long): Single<SimpleVO>
}