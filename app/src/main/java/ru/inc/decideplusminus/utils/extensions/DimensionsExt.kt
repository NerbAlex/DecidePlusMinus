package ru.inc.decideplusminus.utils.extensions

import android.content.res.Resources
import android.util.TypedValue

internal fun Int.toPxF(): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

internal fun Int.toPx(): Int = toPxF().toInt()