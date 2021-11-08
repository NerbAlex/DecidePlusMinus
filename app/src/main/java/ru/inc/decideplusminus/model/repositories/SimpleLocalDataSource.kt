package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem

interface SimpleLocalDataSource {

    fun downloadData(): Single<List<BaseSimpleItem>>
    fun updateData(baseSimpleItem: BaseSimpleItem): Completable

}