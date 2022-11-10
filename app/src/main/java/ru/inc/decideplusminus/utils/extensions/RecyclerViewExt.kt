package ru.inc.decideplusminus.utils.extensions

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

internal fun RecyclerView.scrollOrSmoothScrollToPosition(targetPosition: Int, smoothScrollLimit: Int = 20) {
    (layoutManager as? LinearLayoutManager)?.run {
        val lastVisiblePosition = findLastVisibleItemPosition()
        if (abs(lastVisiblePosition - targetPosition) < smoothScrollLimit) {
            smoothScrollToPosition(targetPosition)
        } else {
            scrollToPosition(targetPosition)
        }
    }
}

internal fun isViewOnVisibleScreen(recyclerView: RecyclerView, view: View): Boolean {
    val lm = recyclerView.layoutManager as LinearLayoutManager
    val firstVisiblePosition = lm.findFirstVisibleItemPosition()
    val lastVisiblePosition = lm.findLastVisibleItemPosition()
    val viewPosition = lm.getPosition(view)
    return viewPosition in firstVisiblePosition..lastVisiblePosition
}