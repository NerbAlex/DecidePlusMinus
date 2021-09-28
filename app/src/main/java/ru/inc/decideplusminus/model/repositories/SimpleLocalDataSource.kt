package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem

interface SimpleLocalDataSource {

    fun downloadData(): Single<List<BaseSimpleSolutionItem>>
    fun updateData(baseSimpleSolutionItem: BaseSimpleSolutionItem): Completable

}