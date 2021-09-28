package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem
import ru.inc.decideplusminus.ui.simple.SimpleSolution
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SolutionRepository

class SimpleSolutionRepositoryImpl(val simple: SimpleLocalDataSource) :
    SolutionRepository<SimpleViewState> {

    private val list = listOf<SimpleSolution>(
        SimpleSolution(
            id = 1,
            type = BaseSimpleSolutionItem.POSITIVE,
            name = "Поехать на море",
            percent = "57%"
        ),
        SimpleSolution(
            id = 2,
            type = BaseSimpleSolutionItem.NEGATIVE,
            name = "Купить лодку",
            percent = "75%"
        ),
        SimpleSolution(
            id = 3,
            type = BaseSimpleSolutionItem.NEGATIVE,
            name = "Зарегаться на баду",
            percent = "88%"
        ),
        SimpleSolution(
            id = 1,
            type = BaseSimpleSolutionItem.POSITIVE,
            name = "Найти девушку",
            percent = "63%"
        ),
        SimpleSolution(
            id = 1,
            type = BaseSimpleSolutionItem.POSITIVE,
            name = "Сходить в кино на Venom2",
            percent = "70%"
        ),
    )

    override fun downloadData(): Single<SimpleViewState> =
        Single.just(SimpleViewState.Success(list))


    override fun updateData(baseSimpleSolutionItem: BaseSimpleSolutionItem): Completable {
        TODO()
    }
}