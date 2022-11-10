package ru.inc.decideplusminus.presentation.ui.simple

import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO

interface SimpleAdapterListener

data class SimpleListener(
    val clickAddArgument: (SimpleVO) -> Unit,
    val clickOpenDetailsArguments: (SimpleVO) -> Unit,
    val clickADeleteArgument: (SimpleVO) -> Unit,
) : SimpleAdapterListener

data class SimpleDetailsListener(
    val click: (SimpleDetailsVO) -> Unit,
    val delete: (SimpleDetailsVO) -> Unit,
) : SimpleAdapterListener
