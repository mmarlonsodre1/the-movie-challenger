package com.example.list_feature.my_list

import com.example.domain.model.movie.MovieDetailModel

object MyListViewModelDummy {
    private val MOVIE_DETAIL_DUMMY = MovieDetailModel(id = 1)
    val MOVIE_DETAIL_LIST_DUMMY = listOf(MOVIE_DETAIL_DUMMY)
}