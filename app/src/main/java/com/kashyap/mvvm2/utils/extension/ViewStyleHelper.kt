package com.kashyap.mvvm2.utils.extension

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import androidx.databinding.BindingAdapter


/**
 * borderWidth :: IN DP
 * cornerRadius :: IN DP
 * **/
fun View.applyViewStyle(backgroundColor: Int?, borderColor: Int?, borderWidth: Float?, cornerRadius: Float?) {
    val borderWidthInPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        borderWidth ?: 0f,
        context.resources.displayMetrics
    )

    val cornerRadiusInPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        cornerRadius ?: 0f,
        context.resources.displayMetrics
    )

    val drawable = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setColor(backgroundColor ?: Color.TRANSPARENT)
        setStroke(borderWidthInPx.toInt(), borderColor ?: Color.TRANSPARENT)
        this.cornerRadius = cornerRadiusInPx
    }
    background = drawable
}

@BindingAdapter(
    "backgroundColor",
    "borderColor",
    "borderWidth",
    "cornerRadius",
    requireAll = false
)
fun View.setViewStyle(
    backgroundColor: Int?,
    borderColor: Int?,
    borderWidth: Float?,
    cornerRadius: Float?
) {
    this.applyViewStyle(backgroundColor, borderColor, borderWidth, cornerRadius)
}