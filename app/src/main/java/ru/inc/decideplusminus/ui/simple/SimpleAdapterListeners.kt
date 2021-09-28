package ru.inc.decideplusminus.ui.simple

interface SimpleAdapterListeners {
    fun clickAddArgument(): (SimpleSolution) -> Unit
    fun clickOpenDetailsArguments(): (SimpleSolution) -> Unit
}