package com.example.domain.model.search

import com.example.domain.model.movie.MovieDetailModel

data class SearchModel(
    val page: Int? = null,
    val results: List<MovieDetailModel>? = null
)