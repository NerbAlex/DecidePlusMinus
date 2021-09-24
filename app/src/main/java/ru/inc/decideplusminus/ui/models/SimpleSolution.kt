package ru.inc.decideplusminus.ui.models

/**
 * Модель простого решения в главном списке
 *
 * @param id - идентификатор
 * @param name - название решения
 */
data class SimpleSolution(
    val id: Long,
    val name: String,
    val solutionPlusList: List<SolutionItem>,
    val solutionMinusList: List<SolutionItem>
)
