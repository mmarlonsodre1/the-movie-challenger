package com.example.base_feature.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppGenderModel(
    val id : Int?,
    val name : String?,
) : Parcelable