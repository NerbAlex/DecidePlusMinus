package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionUseCase
import javax.inject.Inject
import kotlin.math.roundToInt

class InsertSolutionToSimpleDetailsUseCaseImpl @Inject constructor(
    private val repository: SimpleRepository
) : InsertSolutionUseCase {


    override fun plus(solutionId: Long, argumentLvl: Int, name: String): Completable {
        return repository.getSimpleSolution(solutionId).flatMapCompletable { simpleVO ->
            // TODO прочекать на каких потоках все исполняется и добавить поток(и)
            return@flatMapCompletable updateSimpleVO(simpleVO as SimpleVO, argumentLvl, true)
                .andThen(insertDetailSolution(solutionId, argumentLvl, name, true))
        }
    }

    private fun updateSimpleVO(simpleVO: SimpleVO, argumentLvl: Int, isPositive: Boolean): Completable {
        val newVO = if (isPositive) {
            updateVO((simpleVO.positiveCount + argumentLvl).toDouble(), simpleVO.negativeCount.toDouble(), simpleVO)
        } else {
            updateVO(simpleVO.positiveCount.toDouble(), (simpleVO.negativeCount + argumentLvl).toDouble(), simpleVO)
        }
        // TODO проверить здесь очередность исполнения кода, что добавится новое VO из условного оператора

        return repository.updateSimpleVo(newVO)
    }

    private fun insertDetailSolution(solutionId: Long, argumentLvl: Int, name: String, isPositive: Boolean): Completable {
        val type = if (isPositive) BaseSimpleItem.DETAILS_POSITIVE else BaseSimpleItem.DETAILS_NEGATIVE
        // TODO сразу делать ДТО модели или в дата слое?
        val vo = SimpleDetailsVO(
            id = System.nanoTime(),
            type = type,
            name = name,
            parentId = solutionId,
            argumentLvl = argumentLvl,
            argumentDescription = "description"
        )
        return repository.insertSimpleDetail(vo)
    }

    override fun minus(solutionId: Long, argumentLvl: Int, name: String): Completable {
        return repository.getSimpleSolution(solutionId).flatMapCompletable { simpleVO ->

            return@flatMapCompletable updateSimpleVO(simpleVO as SimpleVO, argumentLvl, false)
                .andThen(insertDetailSolution(solutionId, argumentLvl, name, false))
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