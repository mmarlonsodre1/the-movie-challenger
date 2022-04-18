package com.example.home_feature.pages.detail

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel

object MovieDetailViewModelDummy {
    val MOVIE_DETAIL_DUMMY = MovieDetailModel(id = 1)
    val MOVIE_DETAIL_LIST_DUMMY = listOf(MOVIE_DETAIL_DUMMY)
    val MOVIE_DETAIL_EMPTY_LIST_DUMMY = emptyList<MovieDetailModel>()
    val SEARCH_DUMMY = SearchModel()
}