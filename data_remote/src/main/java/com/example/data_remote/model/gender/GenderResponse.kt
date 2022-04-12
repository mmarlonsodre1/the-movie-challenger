package com.example.data_remote.model.gender

import com.google.gson.annotations.SerializedName

data class GenderResponse(
    @SerializedName("id")
    val id : Int?,
    @SerializedName("name")
    val name : String?,
)