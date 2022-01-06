package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.ui.models.TeacherSimpleItem
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleVO
import ru.inc.decideplusminus.view_model.simple.AddInnerSimpleRepository
import ru.inc.decideplusminus.view_model.simple.CreateSimpleRepository
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SimpleRepository

object SimpleRepositoryImpl :  SimpleRepository, CreateSimpleRepository, AddInnerSimpleRepository {

    override val simplePS: PublishSubject<SimpleViewState> = PublishSubject.create()

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

    override fun downloadData() {
        val newList: MutableList<BaseSimpleItem> = mutableListOf()
        newList.addAll(list)
        simplePS.onNext(SimpleViewState.Success(newList))
    }


    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO()
    }

    override fun createSimpleSolution(name: String): Completable {
        val newVO = SimpleVO(
            id = 6,
            type = BaseSimpleItem.NEUTRAL,
            name = name,
            percent = ""
        )
        list.add(newVO)
        downloadData()
        return Completable.complete()
    }
}