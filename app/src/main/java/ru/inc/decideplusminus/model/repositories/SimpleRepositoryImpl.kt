package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.ui.models.TeacherSimpleItem
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleVO
import ru.inc.decideplusminus.view_model.simple.CreateSimpleRepository
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SimpleRepository

object SimpleRepositoryImpl :  SimpleRepository, CreateSimpleRepository {

    fun test() {

    }

    private val list = mutableListOf(
        TeacherSimpleItem(
            id = 0,
            type = BaseSimpleItem.TEACHER,
            isActive = true
        ),
        SimpleVO(
            id = 1,
            type = BaseSimpleItem.POSITIVE,
            name = "Поехать на море",
            percent = "57%"
        ),
        SimpleVO(
            id = 2,
            type = BaseSimpleItem.NEGATIVE,
            name = "Купить лодку",
            percent = "75%"
        ),
        SimpleVO(
            id = 3,
            type = BaseSimpleItem.NEGATIVE,
            name = "Зарегаться на баду",
            percent = "88%"
        ),
        SimpleVO(
            id = 4,
            type = BaseSimpleItem.POSITIVE,
            name = "Найти девушку",
            percent = "63%"
        ),
        SimpleVO(
            id = 5,
            type = BaseSimpleItem.POSITIVE,
            name = "Сходить в кино на Venom2",
            percent = "70%"
        ),
    )

    override fun downloadData(): Single<SimpleViewState> =
        Single.just(SimpleViewState.Success(list))


    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO()
    }

    override fun createSimpleSolution(name: String): Completable {
        list.add(SimpleVO(
            id = 6,
            type = BaseSimpleItem.NEUTRAL,
            name = name,
            percent = ""
        ))
        return Completable.complete()
    }
}