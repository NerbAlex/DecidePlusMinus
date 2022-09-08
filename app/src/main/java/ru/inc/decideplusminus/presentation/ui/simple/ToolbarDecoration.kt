package ru.inc.decideplusminus.presentation.ui.simple

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.inc.decideplusminus.utils.extensions.toPx

class ToolbarDecoration : RecyclerView.ItemDecoration() {
    companion object {
        private val TOOLBAR_PADDING = 60.toPx()
    }
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) outRect.top = TOOLBAR_PADDING
    }
}