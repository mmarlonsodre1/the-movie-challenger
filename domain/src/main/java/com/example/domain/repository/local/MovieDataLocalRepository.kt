package com.example.domain.repository.local

import com.example.domain.model.movie.MovieDetailModel
import kotlinx.coroutines.flow.Flow

interface MovieDataLocalRepository {
    fun addMovie(movie: MovieDetailModel)
    fun getAllMovies() : Flow<List<MovieDetailModel>>
    fun deleteMovie(movieId: Int)
}