package com.example.domain.model.search

import com.example.domain.model.movie.MovieDetailModel

data class SearchModel(
    val page: Int?,
    val results: List<MovieDetailModel>?
)