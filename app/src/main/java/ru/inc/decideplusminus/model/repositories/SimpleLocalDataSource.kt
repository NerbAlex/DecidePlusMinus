package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.base.BaseItem

interface SimpleLocalDataSource {

    fun downloadData(): Single<List<BaseItem>>
    fun updateData(baseItem: BaseItem): Completable

}