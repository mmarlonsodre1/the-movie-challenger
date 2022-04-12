package com.example.data.datasource.remote

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getMovieList(genderId: Int) : Flow<MovieListModel>
    fun getMovieDetail(movieId: Int) : Flow<MovieDetailModel>
}