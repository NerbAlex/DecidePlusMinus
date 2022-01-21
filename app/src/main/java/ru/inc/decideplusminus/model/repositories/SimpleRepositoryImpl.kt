package ru.inc.decideplusminus.model.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.ui.models.TeacherSimpleItem
import ru.inc.decideplusminus.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.ui.simple.SimpleVO
import ru.inc.decideplusminus.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.view_model.simple.AddInnerSimpleRepository
import ru.inc.decideplusminus.view_model.simple.CreateSimpleRepository
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SimpleRepository
import ru.inc.decideplusminus.view_model.simple.details.SimpleDetailsRepository
import ru.inc.decideplusminus.view_model.simple.details.SimpleDetailsViewState
import kotlin.math.roundToInt
import kotlin.random.Random

object SimpleRepositoryImpl : SimpleRepository, CreateSimpleRepository, AddInnerSimpleRepository,
    SimpleDetailsRepository {

    override val simplePS: PublishSubject<SimpleViewState> = PublishSubject.create()

    fun test() {

    }

    //TODO сохранение вложенного аргумента с текстом
    private val listInnerSolution = mutableMapOf<Long, MutableList<SimpleDetailsVO>>()
    private val listSolution = mutableListOf<BaseSimpleItem>()


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

    override fun plus(solutionId: Long, argumentLvl: Int, name: String): Completable {
        updateSolutions(solutionId, argumentLvl, true)
        insertDetailsSolution(solutionId, argumentLvl, name, true)
        downloadData()
        return Completable.complete()
    }

    private fun insertDetailsSolution(solutionId: Long, argumentLvl: Int, name: String, isPositive: Boolean) {
        val type = if (isPositive) BaseSimpleItem.DETAILS_POSITIVE else BaseSimpleItem.DETAILS_NEGATIVE
        val vo = SimpleDetailsVO(
            id = System.nanoTime(),
            type = type,
            name = name,
            parentId = solutionId,
            argumentLvl = argumentLvl,
            argumentDescription = "description"
        )

        var list = mutableListOf<SimpleDetailsVO>()
        listInnerSolution[solutionId]?.let {
            list = it
            list.add(vo)
        } ?: list.add(vo)

        listInnerSolution[solutionId] = list
    }

    override fun minus(solutionId: Long, argumentLvl: Int, name: String): Completable {
        updateSolutions(solutionId, argumentLvl, false)
        insertDetailsSolution(solutionId, argumentLvl, name, false)
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

    override fun searchSolutionById(id: Long): Single<SimpleDetailsViewState> {
        val allList = listInnerSolution[id]
        val positiveList = allList?.filter { it.type == BaseSimpleItem.DETAILS_POSITIVE }
        val negativeList = allList?.filter { it.type == BaseSimpleItem.DETAILS_NEGATIVE }
        return Single.just(
            SimpleDetailsViewState.SuccessLists(
                positiveList = positiveList,
                negativeList = negativeList
            )
        )
    }
}