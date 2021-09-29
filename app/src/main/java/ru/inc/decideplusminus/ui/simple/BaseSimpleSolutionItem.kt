package ru.inc.decideplusminus.ui.simple

abstract class BaseSimpleSolutionItem {
    //TODO оставить, в будущем здесь появятся другие виджеты
    companion object {
        const val POSITIVE = 1
        const val NEGATIVE = 2
        const val TEACHER = 3

    }

    abstract val id: Long
    abstract val type: Int
}