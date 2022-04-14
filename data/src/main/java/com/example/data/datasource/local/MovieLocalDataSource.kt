package com.example.data.datasource.local

import com.example.domain.model.movie.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun addMovie(movie: MovieDetailModel)
    fun getAllMovies() : Flow<List<MovieDetailModel>>
    fun deleteMovie(movieId: Int)
}