package com.example.data_remote.model.gender

import com.google.gson.annotations.SerializedName

data class GenderListResponse(
    @SerializedName("genres")
    val genres : List<GenderResponse>?,
)