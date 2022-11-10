package ru.inc.decideplusminus.utils.extensions

import android.view.View
import androidx.core.view.isVisible

/**
 * Для взаимодействия с View после показа на экране
 */
internal inline fun<T: View> T.afterShowOrGone(isShow: Boolean, block: T.() -> Unit) {
    visibility = when (isShow) {
        true -> View.VISIBLE
        else -> View.GONE
    }
    if (isVisible) {
        block.invoke(this)
    }
}