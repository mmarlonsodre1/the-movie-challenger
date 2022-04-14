package com.example.base_feature.utils

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

val Context.layoutInflater
    get() = LayoutInflater.from(this)

fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

fun View.marginDP(safeOld: Boolean = false, left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = if (safeOld) this + leftMargin else this}
        top?.run { topMargin = if (safeOld) this + topMargin else this }
        right?.run { rightMargin = if (safeOld) this + rightMargin else this }
        bottom?.run { bottomMargin = if (safeOld) this + bottomMargin else this }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()