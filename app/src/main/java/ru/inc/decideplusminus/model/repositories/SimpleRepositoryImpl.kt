package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.ui.models.TeacherSimpleItem
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleVO
import ru.inc.decideplusminus.ui.simple.solution.SolutionItem
import ru.inc.decideplusminus.view_model.simple.AddInnerSimpleRepository
import ru.inc.decideplusminus.view_model.simple.CreateSimpleRepository
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SimpleRepository
import kotlin.math.roundToInt

object SimpleRepositoryImpl : SimpleRepository, CreateSimpleRepository, AddInnerSimpleRepository {

    override val simplePS: PublishSubject<SimpleViewState> = PublishSubject.create()

    fun test() {

    }

    private val listInnerSolution = mutableListOf<SolutionItem>()
    private val listSolution = mutableListOf<BaseSimpleItem>()

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
        newList.addAll(listSolution)
        simplePS.onNext(SimpleViewState.Success(newList))
    }


    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO()
    }

    override fun createSimpleSolution(name: String): Completable {
        val newVO = SimpleVO(
            id = System.currentTimeMillis(),
            type = BaseSimpleItem.NEUTRAL,
            name = name,
            percent = ""
        )
        listSolution.add(newVO)
        downloadData()
        return Completable.complete()
    }

    override fun plus(solutionId: Long?, argumentLvl: Int): Completable {
        updateSolutions(solutionId, argumentLvl, true)
        downloadData()
        return Completable.complete()
    }

    override fun minus(solutionId: Long?, argumentLvl: Int): Completable {
        updateSolutions(solutionId, argumentLvl, false)
        downloadData()
        return Completable.complete()
    }

    private fun updateSolutions(id: Long?, argumentLvl: Int, isPositive: Boolean) {
        listSolution.find { it.id == id }
            ?.let { vo ->
                if (vo is SimpleVO) {
                    val newVO = if (isPositive) {
                        updateVO((vo.positiveCount + argumentLvl).toDouble(), vo.negativeCount.toDouble(), vo)
                    } else updateVO(vo.positiveCount.toDouble(), (vo.negativeCount + argumentLvl).toDouble(), vo)

                    val changeIndex = listSolution.indexOf(vo)
                    listSolution[changeIndex] = newVO
                }
            }


    }

    private fun updateVO(positiveCount: Double, negativeCount: Double, vo: SimpleVO): SimpleVO {
        val sum = positiveCount + negativeCount

        val percentAndType = when {
            positiveCount > negativeCount -> {
                val percent = calculatePercent(positiveCount, sum)
                val type = BaseSimpleItem.POSITIVE
                Pair(percent, type)
            }
            positiveCount == negativeCount -> {
                val percent = "50%"
                val type = BaseSimpleItem.NEUTRAL
                Pair(percent, type)
            }
            else -> {
                val percent = calculatePercent(negativeCount, sum)
                val type = BaseSimpleItem.NEGATIVE
                Pair(percent, type)
            }
        }
        return vo.copy(
            percent = percentAndType.first,
            type = percentAndType.second,
            positiveCount = positiveCount.toInt(),
            negativeCount = negativeCount.toInt()
        )
    }

    private fun calculatePercent(count: Double, sum: Double): String {
        val calc = (count / sum)
        val percent = calc * 100.00
        return "${percent.roundToInt()}%"
    }

}