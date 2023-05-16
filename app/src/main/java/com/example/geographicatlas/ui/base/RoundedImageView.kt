package com.example.geographicatlas.ui.base

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.example.geographicatlas.R


class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttrs) {

    private val path = Path()
    private var cornerRadius: Float = 0f
    private var corners = floatArrayOf()

    init {
        scaleType = ScaleType.CENTER_CROP
        context.withStyledAttributes(attrs, R.styleable.RoundedImageView) {
            cornerRadius = getDimension(R.styleable.RoundedImageView_cornerRadius, 0F)
        }
        this.corners = floatArrayOf(
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius,
            cornerRadius
        )
    }

    override fun onDraw(canvas: Canvas) {
        path.addRoundRect(
            0f,
            0f,
            width.toFloat(),
            height.toFloat(),
            corners,
            Path.Direction.CW
        )

        canvas.clipPath(path)
        super.onDraw(canvas)
    }
}