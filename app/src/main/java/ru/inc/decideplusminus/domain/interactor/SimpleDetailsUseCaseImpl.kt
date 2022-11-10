package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsUseCase
import javax.inject.Inject
import kotlin.math.roundToInt

class SimpleDetailsUseCaseImpl @Inject constructor(
    private val repository: SimpleRepository
) : SimpleDetailsUseCase {

    override fun deleteSimpleDetail(vo: SimpleDetailsVO): Completable {
        return repository.deleteSimpleDetail(vo.id).andThen(updateMainSolution(vo))
    }

    private fun updateMainSolution(vo: SimpleDetailsVO): Completable {
        return repository.getSimpleSolution(vo.parentId).flatMapCompletable { oldMainSolution ->
            updateSimpleVO(
                simpleVO = oldMainSolution,
                argumentLvl = vo.argumentLvl,
                isPositive = vo.type == BaseSimpleItem.DETAILS_POSITIVE
            )
        }
    }

    override fun getSimpleDetailsSolutions(parentId: Long): Single<Pair<List<SimpleDetailsVO>, List<SimpleDetailsVO>>> {
        return repository.getSimpleDetailsSolutions(parentId)
    }

    private fun updateSimpleVO(simpleVO: SimpleVO, argumentLvl: Int, isPositive: Boolean): Completable {
        val newVO = if (isPositive) {
            updateSimpleVO(
                positiveCount = (simpleVO.positiveCount - argumentLvl).toDouble(),
                negativeCount = simpleVO.negativeCount.toDouble(),
                vo = simpleVO
            )
        } else {
            updateSimpleVO(
                positiveCount = simpleVO.positiveCount.toDouble(),
                negativeCount = (simpleVO.negativeCount - argumentLvl).toDouble(),
                vo = simpleVO
            )
        }
        return repository.updateSimpleVo(newVO)
    }

    private fun updateSimpleVO(positiveCount: Double, negativeCount: Double, vo: SimpleVO): SimpleVO { // todo вынести в общий с юскейсом добавления
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
                val percent = InsertSolutionToSimpleDetailsUseCaseImpl.DEFAULT_PERCENT
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

    private fun calculatePercent(count: Double, sum: Double): String { // todo вынести в общий с юскейсом добавления
        val calc = (count / sum)
        val percent = calc * InsertSolutionToSimpleDetailsUseCaseImpl.DOUBLE_100
        return "${percent.roundToInt()}%"
    }
}


