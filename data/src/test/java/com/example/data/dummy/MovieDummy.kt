package com.example.data.dummy

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel

object MovieDummy {
    val SEARCH_DUMMY = SearchModel()
    val MOVIE_DETAIL_DUMMY = MovieDetailModel()
    val LIST_MOVIE_DETAIL_DUMMY = listOf(MovieDetailModel())
}