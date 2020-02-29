package com.example.playground

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

/**
 * Created by jesse on 2/28/20.
 * This is a part of the project Android Certification Playground.
 */
open class CustomTypefaceSpan(private val font: Typeface?) : MetricAffectingSpan() {

    override fun updateMeasureState(textPaint: TextPaint) = update(textPaint)

    override fun updateDrawState(textPaint: TextPaint) = update(textPaint)

    private fun update(textPaint: TextPaint) {
        textPaint.apply {
            val old = typeface
            val oldStyle = old?.style ?: 0

            // keep the style set before
            val font = Typeface.create(font, oldStyle)
            typeface = font
        }
    }

}