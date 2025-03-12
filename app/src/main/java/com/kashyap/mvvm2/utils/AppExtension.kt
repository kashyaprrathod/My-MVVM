package com.kashyap.mvvm2.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kashyap.mvvm2.BuildConfig

//Image view
@Suppress("LocalVariableName")
@SuppressLint("CheckResult")
@BindingAdapter("imageUrl", "placeholder", "viewWidth", "viewHeight", "needBaseUrl", "needBlur", requireAll = false)
fun ImageView.loadImage(url: String?, placeholder: Drawable?, view_width: Int?, view_height: Int?, needBaseUrl: Boolean, needBlur: Boolean) {
    try {
        val options = RequestOptions()
        if (view_width != null && view_height != null) options.override(view_width, view_height)
        if (placeholder != null) options.placeholder(placeholder)
        Glide.with(this.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).apply(options).into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

//Log for context
fun Context.loggerD(msg: String) {
    if (BuildConfig.DEBUG)
        Log.d(this.javaClass.simpleName, msg)
}

fun Context.loggerE(msg: String) {
    if (BuildConfig.DEBUG)
        Log.e(this.javaClass.simpleName, msg)
}

fun Context.loggerI(msg: String) {
    if (BuildConfig.DEBUG)
        Log.i(this.javaClass.simpleName, msg)
}

fun Context.loggerV(msg: String) {
    if (BuildConfig.DEBUG)
        Log.v(this.javaClass.simpleName, msg)
}

fun Context.loggerW(msg: String) {
    if (BuildConfig.DEBUG)
        Log.w(this.javaClass.simpleName, msg)
}


fun View.onViewTapHaptic() {
    @Suppress("DEPRECATION")
    performHapticFeedback(
        HapticFeedbackConstants.VIRTUAL_KEY,
        // Ignore device's setting. Otherwise, you can use FLAG_IGNORE_VIEW_SETTING to ignore view's setting.
        HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
    )
}