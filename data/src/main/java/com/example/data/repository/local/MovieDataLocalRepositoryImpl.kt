package com.example.data.repository.local

import com.example.data.datasource.local.MovieLocalDataSource
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.repository.local.MovieDataLocalRepository

class MovieDataLocalRepositoryImpl(
    val dataSource: MovieLocalDataSource
): MovieDataLocalRepository {

    override fun addMovie(movie: MovieDetailModel) = dataSource.addMovie(movie)
    override fun getAllMovies() = dataSource.getAllMovies()
    override fun deleteMovie(movieId: Int) = dataSource.deleteMovie(movieId)
}