package com.example.data.repository

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    val dataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieList(genderId: Int): Flow<MovieListModel> = dataSource.getMovieList(genderId)
    override fun getMovieDetail(movieId: Int): Flow<MovieDetailModel> = dataSource.getMovieDetail(movieId)
}