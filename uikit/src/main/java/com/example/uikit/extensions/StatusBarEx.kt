package com.example.uikit.extensions

import android.content.Context

fun Context.getTopSafeAreaPx() = resources.getIdentifier("navigation_bar_height", "dimen", "android")