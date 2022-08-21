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

    companion object {
        private const val DEFAULT_PERCENT = "50%"
        private const val DOUBLE_100 = 100.00
    }

    override fun plus(solutionId: Long, argumentLvl: Int, name: String): Completable =
        handleSolutions(
            solutionId = solutionId,
            argumentLvl = argumentLvl,
            name = name,
            isPositive = true
        )

    override fun minus(solutionId: Long, argumentLvl: Int, name: String): Completable =
        handleSolutions(
            solutionId = solutionId,
            argumentLvl = argumentLvl,
            name = name,
            isPositive = false
        )

    private fun handleSolutions(
        solutionId: Long,
        argumentLvl: Int,
        name: String,
        isPositive: Boolean
    ): Completable =
        repository.getSimpleSolution(solutionId).flatMapCompletable { simpleVO ->
            updateSimpleVO(
                simpleVO = simpleVO as SimpleVO,
                argumentLvl = argumentLvl,
                isPositive = isPositive
            ).andThen(
                insertDetailSolution(
                    solutionId = solutionId,
                    argumentLvl = argumentLvl,
                    name = name,
                    isPositive = isPositive
                )
            )
        }

    private fun updateSimpleVO(simpleVO: SimpleVO, argumentLvl: Int, isPositive: Boolean): Completable {
        val newVO = if (isPositive) {
            updateSimpleVO(
                positiveCount = (simpleVO.positiveCount + argumentLvl).toDouble(),
                negativeCount = simpleVO.negativeCount.toDouble(),
                vo = simpleVO
            )
        } else {
            updateSimpleVO(
                positiveCount = simpleVO.positiveCount.toDouble(),
                negativeCount = (simpleVO.negativeCount + argumentLvl).toDouble(),
                vo = simpleVO
            )
        }
        return repository.updateSimpleVo(newVO)
    }

    private fun insertDetailSolution(
        solutionId: Long,
        argumentLvl: Int,
        name: String,
        isPositive: Boolean
    ): Completable {
        val type = if (isPositive) BaseSimpleItem.DETAILS_POSITIVE else BaseSimpleItem.DETAILS_NEGATIVE

        val vo = SimpleDetailsVO(
            type = type,
            name = name,
            parentId = solutionId,
            argumentLvl = argumentLvl,
            argumentDescription = "todo 89 ticket" // TODO [PET-SOL-HNDLR-89] - обработка описания
        )
        return repository.insertSimpleDetail(vo)
    }

    private fun updateSimpleVO(positiveCount: Double, negativeCount: Double, vo: SimpleVO): SimpleVO {
        val sum = positiveCount + negativeCount

        val percentAndType = when {
            positiveCount > negativeCount -> {
                val percent = calculatePercent(positiveCount, sum)
                val type = BaseSimpleItem.POSITIVE
                Pair(percent, type)
            }
            positiveCount < negativeCount -> {
                val percent = calculatePercent(negativeCount, sum)
                val type = BaseSimpleItem.NEGATIVE
                Pair(percent, type)
            }
            else -> {
                val percent = DEFAULT_PERCENT
                val type = BaseSimpleItem.NEUTRAL
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
        val percent = calc * DOUBLE_100
        return "${percent.roundToInt()}%"
    }
}