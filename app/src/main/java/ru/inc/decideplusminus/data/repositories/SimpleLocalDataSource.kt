package ru.inc.decideplusminus.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO

interface SimpleLocalDataSource {

    fun getAll(): Single<List<BaseSimpleItem>>
    fun updateData(baseSimpleItem: BaseSimpleItem): Completable
    fun create(simpleSolution: SimpleVO): Completable

}