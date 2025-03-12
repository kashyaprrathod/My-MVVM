package com.kashyap.mvvm2.utils.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SquareSegmentedProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var segments: Int = 5 // Number of segments
    private var currentProgress: Int = 2 // Current progress (number of filled segments)
    private var segmentColor: Int = context.resources.getColor(android.R.color.holo_blue_light) // Segment color
    private var emptySegmentColor: Int = context.resources.getColor(android.R.color.darker_gray) // Empty segment color

    init {
        paint.strokeWidth = 2f // Stroke width for segments
        paint.style = Paint.Style.FILL // Style for segments (filled)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width
        val height = height
        val segmentWidth = width / segments

        // Draw empty segments
        paint.color = emptySegmentColor
        for (i in 0 until segments) {
            val left = i * segmentWidth
            val top = 0
            val right = left + segmentWidth
            val bottom = height

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }

        // Draw filled segments based on current progress
        paint.color = segmentColor
        for (i in 0 until currentProgress) {

            val left = i * segmentWidth
            val top = 0
            val right = left + segmentWidth
            val bottom = height

            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }

    // Method to update current progress and redraw view
    fun setCurrentProgress(progress: Int) {
        if (progress >= 0 && progress <= segments) {
            currentProgress = progress
            invalidate() // Redraw view
        }
    }
}