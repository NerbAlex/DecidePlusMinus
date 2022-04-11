package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState

interface SimpleRepository {
    val simpleMainPagePS: PublishSubject<SimpleMainPageViewState>

    fun getSimpleSolutions(): Single<List<BaseSimpleItem>>
    fun getSimpleSolution(id: Long): Single<BaseSimpleItem>
    fun getSimpleDetailSolution(): Single<SimpleDetailsVO>
    fun getSimpleDetailsSolutions(parentId: Long): Single<List<SimpleDetailsVO>>

    fun updateSimpleVo(newVO: SimpleVO): Completable
    fun createSimpleSolution(simpleSolution: SimpleVO): Completable
    fun insertSimpleDetail(vo: SimpleDetailsVO): Completable
}