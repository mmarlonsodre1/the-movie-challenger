package com.example.data_remote.model.movie

import com.google.gson.annotations.SerializedName

data class ProductionCompanieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val originCountry: String?,
)