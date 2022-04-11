package ru.inc.decideplusminus.frameworks.base.base_presentation

abstract class BaseSimpleItem {
    //TODO оставить, в будущем здесь появятся другие виджеты
    companion object {
        const val POSITIVE = 1
        const val NEGATIVE = 2
        const val TEACHER = 3
        const val NEUTRAL = 4

        const val DETAILS_POSITIVE = 5
        const val DETAILS_NEGATIVE = 6
    }

    abstract val id: Long
    abstract val type: Int
}

//TODO мб перенести вьютайп на енамы
enum class SimpleViewType {
    POSITIVE,
    NEGATIVE,
    TEACHER,
    NEUTRAL,
    DETAILS_POSITIVE,
    DETAILS_NEGATIVE
}