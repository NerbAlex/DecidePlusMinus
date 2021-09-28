package ru.inc.decideplusminus.ui.simple

/**
 * Модель простого решения в главном списке
 *
 * @param id - идентификатор
 * @param name - название решения
 * @param type - текущий статус за/против и тип VH
 * @param percent - процент за/против
 */
data class SimpleSolution(
    override val id: Long,
    override val type: Int,
    val name: String,
    val percent: String
): BaseSimpleSolutionItem()
