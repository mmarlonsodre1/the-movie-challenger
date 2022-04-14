package com.example.domain.usecase.local.movie

import com.example.domain.core.UseCase
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.repository.local.MovieDataLocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetAllMoviesDataLocalUserCase(
    scope: CoroutineScope,
    private val repository: MovieDataLocalRepository
) : UseCase<List<MovieDetailModel>, Unit>(scope) {

    override fun run(params: Unit?): Flow<List<MovieDetailModel>> = repository.getAllMovies()
}