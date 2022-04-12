package com.example.domain.repository

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(genderId: Int) : Flow<MovieListModel>
    fun getMovieDetail(movieId: Int) : Flow<MovieDetailModel>
}