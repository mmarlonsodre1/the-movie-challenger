package com.example.domain.usecase.remote.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.search.SearchModel
import com.example.domain.repository.remote.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetSimilarMoviesUserCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<SearchModel, GetSimilarMoviesUserCase.Params>(scope) {

    override fun run(params: Params?): Flow<SearchModel> = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getSimilarMovies(params.movieId)
    }

    data class Params(
        val movieId: Int,
    )
}