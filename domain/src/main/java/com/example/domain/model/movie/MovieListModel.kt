package com.example.domain.model.movie

data class MovieListModel(
    val id: Int?,
    val page: Int?,
    val results: List<MovieItemListModel>?,
    val totalPages: Int?,
    val totalResults: Int?
)