package ru.inc.decideplusminus.presentation.ui.simple.details

import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem

/**
 * Модель решения
 *
 * @param id - Идентификатор
 * @param parentId - Идентификатор родителя, для связи
 * @param argumentLvl - уровень аргумента (от 1 до 3)
 * @param argumentDescription - Описание аргумента, опциональное поле, если null, не показывать иконку описания
 */
data class SimpleDetailsVO(
    override val id: Long,
    override val type: Int,
    val name: String,
    val parentId: Long,
    val argumentLvl: Int,
    val argumentDescription: String?
): BaseSimpleItem()