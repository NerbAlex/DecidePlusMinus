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
private const val MOTION_UP_VALUE = -200f
private const val MOTION_DOWN_VALUE = 200f
private const val SCROLL_PROCESS_DELAY = 2000L

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

internal fun View.motionUp(duration: Long = DEFAULT_MOTION_DURATION, onCompleted: () -> Unit) {
    animate().apply {
        yBy(MOTION_UP_VALUE)
        this.duration = duration
        withEndAction { onCompleted.invoke() }
    }
}

internal fun View.motionDown(duration: Long = DEFAULT_MOTION_DURATION, onCompleted: () -> Unit) {
    animate().apply {
        yBy(MOTION_DOWN_VALUE)
        this.duration = duration
        withEndAction { onCompleted.invoke() }
    }
}

internal fun Fragment.toolbarAnimationWithRecyclerView(
    recyclerView: RecyclerView,
) {
    recyclerView.addItemDecoration(ToolbarDecoration())

    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        val decoration: RecyclerView.ItemDecoration = ToolbarDecoration()

        val toolbar = requireActivity().findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        var isToolbarVisible = true
        var isMotionUpAnimationProcess = false
        var isMotionDownAnimationProcess = false
        var startDragTime: Long = 0
        var isIdle = false

        private fun repeatCheckMotionDownAnimation() {
            toolbar.postDelayed(
                {
                    val lastDragTime = System.currentTimeMillis() - startDragTime

                    if (lastDragTime < SCROLL_PROCESS_DELAY) {
                        repeatCheckMotionDownAnimation()
                    } else {
                        if (isToolbarVisible || isMotionDownAnimationProcess) return@postDelayed

                        isMotionDownAnimationProcess = true
                        toolbar.motionDown {
                            recyclerView.addItemDecoration(decoration)
                            isMotionDownAnimationProcess = false
                            isToolbarVisible = true
                        }
                    }
                }, SCROLL_PROCESS_DELAY
            )
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                startDragTime = System.currentTimeMillis()
                isIdle = false

                if (isToolbarVisible && !isMotionUpAnimationProcess && !isMotionDownAnimationProcess) {
                    isMotionUpAnimationProcess = true
                    toolbar.motionUp {
                        recyclerView.removeItemDecorationAt(0)
                        isMotionUpAnimationProcess = false
                        isToolbarVisible = false
                        if (isIdle && !isMotionDownAnimationProcess) {
                            repeatCheckMotionDownAnimation()
                        }
                    }
                }
            }

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                isIdle = true
                if (isMotionUpAnimationProcess || isToolbarVisible || isMotionDownAnimationProcess) return
                repeatCheckMotionDownAnimation()
            }
        }
    })
}