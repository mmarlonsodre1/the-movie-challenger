package com.example.data.datasource.remote

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getMovieDetail(movieId: Int) : Flow<MovieDetailModel>
    fun getSimilarMovies(movieId: Int): Flow<SearchModel>
    fun getTopMovies(): Flow<SearchModel>
    fun getTrendingMovies(): Flow<SearchModel>
    fun getPopularMovies(): Flow<SearchModel>
    fun getUpComingMovies(): Flow<SearchModel>
}