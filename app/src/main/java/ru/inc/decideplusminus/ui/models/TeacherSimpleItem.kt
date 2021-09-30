package ru.inc.decideplusminus.ui.models

import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem

/**
 * Модель кнопки обучения
 */
data class TeacherSimpleItem(
    override val id: Long,
    override val type: Int,
    var isActive: Boolean
) : BaseSimpleSolutionItem()
