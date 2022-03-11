package ru.inc.decideplusminus.presentation.ui.simple.teachers

import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem

/**
 * Модель кнопки обучения
 */
data class TeacherSimpleItem(
    override val id: Long,
    override val type: Int,
    var isActive: Boolean
) : BaseSimpleItem()
