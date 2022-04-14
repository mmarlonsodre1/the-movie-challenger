package com.example.domain.repository.remote

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetail(movieId: Int) : Flow<MovieDetailModel>
    fun getSimilarMovies(movieId: Int): Flow<SearchModel>
    fun getTopMovies(): Flow<SearchModel>
    fun getTrendingMovies(): Flow<SearchModel>
    fun getPopularMovies(): Flow<SearchModel>
    fun getUpComingMovies(): Flow<SearchModel>
}