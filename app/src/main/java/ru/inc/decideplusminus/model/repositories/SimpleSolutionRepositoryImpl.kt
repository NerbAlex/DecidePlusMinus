package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SolutionRepository

class SimpleSolutionRepositoryImpl (val simple: SimpleLocalDataSource) :
    SolutionRepository<SimpleViewState> {


    override fun downloadData(): Single<SimpleViewState> {
            TODO()
    }

    override fun updateData(baseSimpleSolutionItem: BaseSimpleSolutionItem): Completable {
        TODO()
    }
}