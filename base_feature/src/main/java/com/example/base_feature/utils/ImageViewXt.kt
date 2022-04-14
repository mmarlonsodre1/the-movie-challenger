package com.example.base_feature.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.base_feature.BuildConfig
import jp.wasabeef.glide.transformations.BlurTransformation

fun ImageView.loadUrl(
    url: String?,
    hasBlur: Boolean = false
) {
    Glide.with(this)
        .load(BuildConfig.IMAGE_BASE_URL + url)
        .placeholder(com.example.uikit.R.drawable.img_placeholder)
        .error(com.example.uikit.R.drawable.not_image)
        .centerCrop()
        .fitCenter()

        .apply {
            if (hasBlur) apply(RequestOptions.bitmapTransform(BlurTransformation(15, 2)))
        }
        .into(this)
}