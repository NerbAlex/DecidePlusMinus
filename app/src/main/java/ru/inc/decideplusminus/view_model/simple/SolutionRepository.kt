package ru.inc.decideplusminus.view_model.simple

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem

interface SolutionRepository<State> {

    fun downloadData(): Single<State>
    fun updateData(baseSimpleSolutionItem: BaseSimpleSolutionItem): Completable

}