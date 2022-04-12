package com.example.data_remote.model.movie

import com.google.gson.annotations.SerializedName

data class MovieItemListResponse(
    @SerializedName("description")
    val description: String?,
    @SerializedName("favorite_count")
    val favoriteCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("item_count")
    val itemCount: Int?,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("list_type")
    val listType: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?
)