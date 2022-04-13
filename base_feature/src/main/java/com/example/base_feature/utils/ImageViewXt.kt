package com.example.base_feature.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base_feature.BuildConfig

fun ImageView.loadUrl(
    url: String?
) {
    Glide.with(this)
        .load(BuildConfig.IMAGE_BASE_URL + url)
        .placeholder(com.example.uikit.R.drawable.img_placeholder)
        .error(com.example.uikit.R.drawable.not_image)
        .centerCrop()
        .fitCenter()
        .into(this)
}