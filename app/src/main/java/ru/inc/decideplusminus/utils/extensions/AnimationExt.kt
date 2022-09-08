package ru.inc.decideplusminus.utils.extensions

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import ru.inc.decideplusminus.R
import ru.inc.decideplusminus.presentation.ui.simple.ToolbarDecoration

private const val DEFAULT_RECYCLER_DELAY = 0.15f
private const val ALPHA_INVISIBLE = 0f
private const val ALPHA_VISIBLE = 1f
private const val DEFAULT_FADE_DURATION = 300L
private const val DEFAULT_MOTION_DURATION = 600L
private const val DEFAULT_ON_COMPLETE_DELAY = 100L
private const val MOTION_UP_VALUE = -200f
private const val MOTION_DOWN_VALUE = 200f

fun Fragment.createDefaultRecyclerAnimation(): LayoutAnimationController =
    LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), R.anim.item_anim)).apply {
        delay = DEFAULT_RECYCLER_DELAY
        order = LayoutAnimationController.ORDER_NORMAL
    }

internal fun View.fadeIn(duration: Long = DEFAULT_FADE_DURATION, onCompleted: () -> Unit) {
    if (visibility == View.VISIBLE) return
    alpha = ALPHA_INVISIBLE
    visibility = View.VISIBLE
    animate().apply {
        alpha(ALPHA_VISIBLE)
        this.duration = duration
        interpolator = FastOutLinearInInterpolator()
        withEndAction { onCompleted.invoke() }
    }
}

internal fun View.fadeOut(duration: Long = DEFAULT_FADE_DURATION, onCompleted: () -> Unit) {
    if (visibility == View.GONE) return
    animate().apply {
        alpha(ALPHA_INVISIBLE)
        this.duration = duration
        interpolator = LinearOutSlowInInterpolator()
        withEndAction {
            visibility = View.GONE
            onCompleted.invoke()
        }
    }
}

internal fun View.motionUp(
    duration: Long = DEFAULT_MOTION_DURATION,
    onCompleteDelay: Long = DEFAULT_ON_COMPLETE_DELAY,
    onCompleted: () -> Unit
) {
    animate().apply {
        yBy(MOTION_UP_VALUE)
        this.duration = duration
        withEndAction { postDelayed(onCompleted, onCompleteDelay) }
    }
}

internal fun View.motionDown(duration: Long = DEFAULT_MOTION_DURATION, onCompleted: () -> Unit) {
    animate().apply {
        yBy(MOTION_DOWN_VALUE)
        this.duration = duration
        withEndAction { onCompleted.invoke() }
    }
}

fun Fragment.toolbarAnimationWithRecyclerView(
    recyclerView: RecyclerView,
    decoration: RecyclerView.ItemDecoration = ToolbarDecoration()
) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        var isToolbarVisible = true
        var isAnimationProcess = false

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                if (isToolbarVisible && !isAnimationProcess) {
                    isToolbarVisible = false
                    isAnimationProcess = true
                    recyclerView.removeItemDecorationAt(0)
                    toolbar.motionUp {
                        isAnimationProcess = false
                        if (isToolbarVisible && !isAnimationProcess) {
                            isAnimationProcess = true
                            toolbar.motionDown {
                                recyclerView.addItemDecoration(decoration)
                                isAnimationProcess = false
                            }
                        }
                    }
                }
            }

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (isAnimationProcess) {
                    isToolbarVisible = true
                    return
                }
                if (!isToolbarVisible) {
                    isAnimationProcess = true
                    toolbar.motionDown {
                        recyclerView.addItemDecoration(decoration)
                        isToolbarVisible = true
                        isAnimationProcess = false
                    }
                }
            }
        }
    })
}