package ru.inc.decideplusminus.frameworks.base.base_data

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.data.models.entities.DetailsEntity
import ru.inc.decideplusminus.data.models.entities.SimpleEntity
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.utils.perfomance.bench

abstract class SimpleMapper {

    // TODO сваливать сюда все BaseItem и здесь разбираться

    protected fun SimpleVO.toEntity(): SimpleEntity {
        return SimpleEntity(
            id = id,
            name = name,
            type = type,
            percent = percent,
            positiveCount = positiveCount,
            negativeCount = negativeCount
        )
    }

    protected fun SimpleEntity.toVO(): SimpleVO {
        return SimpleVO(
            id = id,
            name = name,
            type = type,
            percent = percent,
            positiveCount = positiveCount,
            negativeCount = negativeCount
        )
    }

    protected fun SimpleDetailsVO.toEntity() =
        DetailsEntity(
            parentId = parentId,
            type = type,
            name = name,
            argumentLvl = argumentLvl,
            argumentDescription = argumentDescription
        )

    protected fun DetailsEntity.toVo() =
        SimpleDetailsVO(
            id = id,
            type = type,
            name = name,
            parentId = parentId,
            argumentLvl = argumentLvl,
            argumentDescription = argumentDescription
        )

    private fun List<SimpleEntity>.toVoList(): List<BaseSimpleItem> = map { it.toVO() }

    protected fun Single<List<SimpleEntity>>.toVoList(): Single<List<BaseSimpleItem>> =
        flatMap {
            bench("mapper") {
                Single.just(it.toVoList())
            }
        }

    protected fun List<DetailsEntity>.toPositiveVoList(): List<SimpleDetailsVO> =
        filter { it.type == BaseSimpleItem.DETAILS_POSITIVE }
            .map { it.toVo() }


    protected fun List<DetailsEntity>.toNegativeVoList(): List<SimpleDetailsVO> =
        filter { it.type == BaseSimpleItem.DETAILS_NEGATIVE }
            .map { it.toVo() }
}