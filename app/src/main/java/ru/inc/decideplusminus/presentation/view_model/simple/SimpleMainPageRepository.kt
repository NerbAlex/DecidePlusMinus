package ru.inc.decideplusminus.presentation.view_model.simple

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem

interface SimpleMainPageRepository{

    fun downloadData()
    fun updateData(baseSimpleItem: BaseSimpleItem): Completable
    val simpleMainPagePS: PublishSubject<SimpleMainPageViewState>

}