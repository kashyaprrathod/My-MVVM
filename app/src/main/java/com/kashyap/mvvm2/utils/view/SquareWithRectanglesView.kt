package com.kashyap.mvvm2.utils.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView


class StoryStatusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var totalSegmentCount = 2
    private var unOpenedSegmentCount = 2
    private var strokeColor = Color.BLACK
    private var strokeWidth = 4f
    private var cornerRadius = 16f
    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        val rect = RectF(strokeWidth / 2, strokeWidth / 2, width - strokeWidth / 2, height - strokeWidth / 2)
        val imagePaint = Paint().apply {
            isAntiAlias = true
        }

        if (drawable != null) {
            val bitmap = getBitmapFromDrawable(drawable)
            val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            imagePaint.shader = shader
            canvas.drawRoundRect(rect, cornerRadius, cornerRadius, imagePaint)
        }

        drawSegments(canvas, rect)
    }

    private fun drawSegments(canvas: Canvas?, rect: RectF) {
        val strokePaint = Paint().apply {
            style = Paint.Style.STROKE
            color = strokeColor
            strokeWidth = this@StoryStatusView.strokeWidth
            isAntiAlias = true
        }

        path.reset()
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)

        val pathMeasure = PathMeasure(path, false)
        val segmentLength = pathMeasure.length / totalSegmentCount

        for (i in 0 until totalSegmentCount) {
            val segmentPath = Path()
            pathMeasure.getSegment(i * segmentLength, (i + 1) * segmentLength - 10, segmentPath, true) // Adjust the gap between segments
            val bound = RectF()
            segmentPath.computeBounds(bound, true)
            Log.e("TAG", "drawSegments: ${i * segmentLength} :: ${(i + 1) * segmentLength - 10} :: ${bound}")
            if (i < unOpenedSegmentCount) {
                strokePaint.color = Color.RED
                canvas?.drawPath(segmentPath, strokePaint)
            } else {
                strokePaint.color = Color.BLACK
                canvas?.drawPath(segmentPath, strokePaint)
            }
        }
    }

    private fun getBitmapFromDrawable(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    fun setSegmentCount(count: Int) {
        totalSegmentCount = count
        invalidate()
    }
}