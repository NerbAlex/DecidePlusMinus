package ru.inc.decideplusminus.view_model.simple

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

interface SimpleRepository{

    fun downloadData(): Single<SimpleViewState>
    fun updateData(baseSimpleItem: BaseSimpleItem): Completable

}