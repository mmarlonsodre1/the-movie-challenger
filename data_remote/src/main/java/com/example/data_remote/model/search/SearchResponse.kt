package com.example.data_remote.model.search

import com.example.data_remote.model.movie.MovieDetailResponse

data class SearchResponse(
    val page: Int?,
    val results: List<MovieDetailResponse>?
)