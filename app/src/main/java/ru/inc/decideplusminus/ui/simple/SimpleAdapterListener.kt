package ru.inc.decideplusminus.ui.simple

import ru.inc.decideplusminus.ui.simple.details.SimpleDetailsVO

sealed interface SimpleAdapterListener {
    interface SimpleListener: SimpleAdapterListener {
        fun clickAddArgument(): (SimpleVO) -> Unit
        fun clickOpenDetailsArguments(): (SimpleVO) -> Unit
    }

    interface SimpleDetailsListener: SimpleAdapterListener {
        fun click(): (SimpleDetailsVO) -> Unit
    }
}
