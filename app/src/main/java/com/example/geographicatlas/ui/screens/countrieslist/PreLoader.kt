package com.example.geographicatlas.ui.screens.countrieslist

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.CycleInterpolator
import androidx.core.animation.doOnEnd


class PreLoader(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val circleRadius = 20F
    private var firstAnimatedValue = 0
    private var secondAnimatedValue = 0
    private var thirdAnimatedValue = 0
    private var firstAnimator: ValueAnimator? = null
    private var secondAnimator: ValueAnimator? = null
    private var thirdAnimator: ValueAnimator? = null

    init {
        paint.color = Color.BLUE
        paint.strokeWidth = 2f
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas) {

        Log.e(TAG, "onDraw: ", )
        val centerWidht = width / 2F
        val centerHeight = height / 2F
        val firstCircleCenter = centerWidht - 60
        val secondCircleCenter = centerWidht + 60

        canvas.drawCircle(firstCircleCenter, centerHeight, circleRadius + firstAnimatedValue, paint)
        canvas.drawCircle(centerWidht, centerHeight, circleRadius + secondAnimatedValue, paint)
        canvas.drawCircle(secondCircleCenter, centerHeight, circleRadius + thirdAnimatedValue, paint)
        if (firstAnimator == null) {
            startFirstValueAnimator()
        }
    }

    private fun startFirstValueAnimator() {
        if (firstAnimator == null) {
            firstAnimator = ValueAnimator.ofInt(0, 9).apply {
                duration = DURATION
                interpolator = CycleInterpolator(1f)
                addUpdateListener {
                    val animatedValue = it.animatedValue as Int
                    if (animatedValue > 0 && firstAnimatedValue != animatedValue) {
                        firstAnimatedValue = animatedValue
                        invalidate()
                    }
                }
            }
        }
        firstAnimator?.start()
        firstAnimator?.doOnEnd {
            startSecondValueAnimator()
        }
    }

    private fun startSecondValueAnimator() {
        if (secondAnimator == null) {
            secondAnimator = ValueAnimator.ofInt(0, 9).apply {
                duration = DURATION
                interpolator = CycleInterpolator(1f)
                addUpdateListener {
                    val animatedValue = it.animatedValue as Int
                    if (animatedValue > 0 && secondAnimatedValue != animatedValue) {
                        secondAnimatedValue = animatedValue
                        invalidate()
                    }
                }
            }
        }
        secondAnimator?.start()
        secondAnimator?.doOnEnd {
            startThirdValueAnimator()
        }
    }

    private fun startThirdValueAnimator() {
        if (thirdAnimator == null) {
            thirdAnimator = ValueAnimator.ofInt(0, 9).apply {
                duration = DURATION
                interpolator = CycleInterpolator(1f)
                addUpdateListener {
                    val animatedValue = it.animatedValue as Int
                    if (animatedValue > 0 && thirdAnimatedValue != animatedValue) {
                        thirdAnimatedValue = animatedValue
                        invalidate()
                    }
                }
            }
        }
        thirdAnimator?.start()
        thirdAnimator?.doOnEnd {
            startFirstValueAnimator()
        }
    }

    companion object {
        private const val TAG = "PreLoader"
        private const val DURATION = 600L
    }
}