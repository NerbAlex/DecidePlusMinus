package ru.inc.decideplusminus.ui.simple.solution

/**
 * Модель решения
 *
 * @param id - Идентификатор
 * @param parentId - Идентификатор родителя, для связи
 * @param argument - Флаг положительный/отрицательный аргумент
 * @param argumentLvl - уровень аргумента (от 1 до 3)
 * @param argumentDescription - Описание аргумента, опциональное поле, если null, не показывать иконку описания
 */
data class SolutionItem(
    val id: Long,
    val parentId: Long,
    val argument: Boolean,
    val argumentLvl: Int,
    val argumentDescription: String?
)