package ru.inc.decideplusminus.ui.simple

interface SimpleAdapterListeners {
    fun clickAddArgument(): (SimpleVO) -> Unit
    fun clickOpenDetailsArguments(): (SimpleVO) -> Unit
}