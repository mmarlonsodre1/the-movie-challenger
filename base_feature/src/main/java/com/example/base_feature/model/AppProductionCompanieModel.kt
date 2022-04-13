package com.example.base_feature.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppProductionCompanieModel(
    val id: Int?,
    val logoPath: String?,
    val name: String?,
    val originCountry: String?,
) : Parcelable