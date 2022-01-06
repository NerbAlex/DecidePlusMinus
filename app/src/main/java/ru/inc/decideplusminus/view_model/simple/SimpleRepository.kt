package ru.inc.decideplusminus.view_model.simple

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

interface SimpleRepository{

    fun downloadData()
    fun updateData(baseSimpleItem: BaseSimpleItem): Completable
    val simplePS: PublishSubject<SimpleViewState>

}