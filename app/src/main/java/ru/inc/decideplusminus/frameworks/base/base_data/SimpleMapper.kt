package ru.inc.decideplusminus.frameworks.base.base_data

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.data.models.dto.SimpleEntity
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.utils.perfomance.bench

abstract class SimpleMapper {

    // TODO сваливать сюда все BaseItem и здесь разбираться

    fun SimpleVO.toEntity(): SimpleEntity {
        return SimpleEntity(
            id = id,
            name = name,
            type = type,
            percent = percent,
            positiveCount = positiveCount,
            negativeCount = negativeCount
        )
    }

    private fun SimpleEntity.toVO(): SimpleVO {
        return SimpleVO(
            id = id,
            name = name,
            type = type,
            percent = percent,
            positiveCount = positiveCount,
            negativeCount = negativeCount
        )
    }

    private fun List<SimpleEntity>.toVoList(): List<BaseSimpleItem> = map { it.toVO() }

    protected fun Single<List<SimpleEntity>>.toSimpleVoList(): Single<List<BaseSimpleItem>> =
        flatMap {
            bench("mapper") {
                Thread.sleep(1000)
                Single.just(it.toVoList())
            }
        }
}