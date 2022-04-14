package com.example.data.repository.remote

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel
import com.example.domain.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    val dataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieDetail(movieId: Int): Flow<MovieDetailModel> = dataSource.getMovieDetail(movieId)
    override fun getSimilarMovies(movieId: Int): Flow<SearchModel> = dataSource.getSimilarMovies(movieId)
    override fun getTopMovies(): Flow<SearchModel> = dataSource.getTopMovies()
    override fun getTrendingMovies(): Flow<SearchModel> = dataSource.getTrendingMovies()
    override fun getPopularMovies(): Flow<SearchModel> = dataSource.getPopularMovies()
    override fun getUpComingMovies(): Flow<SearchModel> = dataSource.getUpComingMovies()
}