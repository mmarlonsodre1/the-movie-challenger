package com.example.domain.usecase.local.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.repository.local.MovieDataLocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoveMovieDataLocalUserCase(
    scope: CoroutineScope,
    private val repository: MovieDataLocalRepository
) : UseCase<Unit, RemoveMovieDataLocalUserCase.Params>(scope) {

    override fun run(params: Params?): Flow<Unit> = when (params) {
        null -> throw MissingParamsException()
        else -> flow { repository.deleteMovie(params.movieId) }
    }

    data class Params(
        val movieId: Int,
    )
}