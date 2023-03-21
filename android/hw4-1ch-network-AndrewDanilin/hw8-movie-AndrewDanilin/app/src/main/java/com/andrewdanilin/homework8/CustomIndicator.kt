package com.andrewdanilin.homework8

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import android.util.AttributeSet
import android.view.View
import java.io.Serializable

class CustomIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val MAX_HEIGHT = 150
        private const val MIN_HEIGHT = 100
        private const val HEIGHT_DIFF = 5
        private const val RECT_WIDTH = 50
        private const val GAP = 10
    }

    val firstRect = Rect()
    val secondRect = Rect()
    val thirdRect = Rect()

    var rectanglesColor = Color.LTGRAY

    private var viewState = ViewState(AnimationState.FIRST_CHANGING, MAX_HEIGHT, MIN_HEIGHT, MIN_HEIGHT)
    private val paint = Paint()

    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.CustomIndicator, defStyleAttr, defStyleRes)
        try {
            rectanglesColor = a.getColor(R.styleable.CustomIndicator_rectanglesColor, Color.LTGRAY)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawThreeRectangles(canvas)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val savedState = SavedState(superState)
        savedState.viewState = viewState
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        viewState = savedState.viewState
    }

    private fun drawThreeRectangles(canvas: Canvas?) {
        val centerYView = height / 2

        firstRect.set(
            0,
            centerYView - (viewState.firstH / 2),
             RECT_WIDTH,
            centerYView - (viewState.firstH / 2) + viewState.firstH
        )

        secondRect.set(
            firstRect.right + GAP,
            centerYView - (viewState.secondH / 2),
            firstRect.right + GAP + RECT_WIDTH,
            centerYView - (viewState.secondH / 2) + viewState.secondH
        )
        thirdRect.set(
            secondRect.right + GAP,
            centerYView - (viewState.thirdH / 2),
            secondRect.right + GAP + RECT_WIDTH,
            centerYView - (viewState.thirdH / 2) + viewState.thirdH
        )

        paint.color = rectanglesColor
        canvas?.drawRect(firstRect, paint)
        canvas?.drawRect(secondRect, paint)
        canvas?.drawRect(thirdRect, paint)

        changeState()
    }

    private fun changeState() {
        postDelayed({
            viewState.nextViewState()
            invalidate()
        }, 16)
    }

    class SavedState : BaseSavedState {
        lateinit var viewState : ViewState

        constructor(superState: Parcelable?): super(superState)

        constructor(parcel: Parcel?): super(parcel) {
            viewState = parcel?.readSerializable() as ViewState
        }

        override fun writeToParcel(out: Parcel?, flags: Int) {
            super.writeToParcel(out, flags)
            out?.writeSerializable(viewState)
        }

        companion object CREATOR: Parcelable.Creator<SavedState> {
            override fun createFromParcel(source: Parcel?): SavedState {
                return SavedState(source)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }

    }

    class ViewState(
        var state: AnimationState,
        var firstH: Int,
        var secondH: Int,
        var thirdH: Int
    ) : Serializable {

        fun nextViewState() {
            when (state) {
                AnimationState.FIRST_CHANGING -> {
                    firstH -= HEIGHT_DIFF
                    if (checkAndChange(firstH)) {
                        secondH = MAX_HEIGHT
                    }
                }
                AnimationState.SECOND_CHANGING -> {
                    secondH -= HEIGHT_DIFF
                    if (checkAndChange(secondH)) {
                        thirdH = MAX_HEIGHT
                    }
                }
                AnimationState.THIRD_CHANGING -> {
                    thirdH -= HEIGHT_DIFF
                    if (checkAndChange(thirdH)) {
                        firstH = MAX_HEIGHT
                    }
                }
            }
        }

        private fun checkAndChange(height: Int) : Boolean {
            if (height == MIN_HEIGHT) {
                state = state.next()
                return true
            }
            return false
        }
    }

    enum class AnimationState {
        FIRST_CHANGING {
            override fun next() = SECOND_CHANGING
        },
        SECOND_CHANGING {
            override fun next() = THIRD_CHANGING
        },
        THIRD_CHANGING {
            override fun next() = FIRST_CHANGING
        };

        abstract fun next(): AnimationState
    }
}