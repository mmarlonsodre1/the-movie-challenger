package com.example.domain.usecase.local.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.repository.local.MovieDataLocalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddMovieDataLocalUserCase(
    scope: CoroutineScope,
    private val repository: MovieDataLocalRepository
) : UseCase<Unit, AddMovieDataLocalUserCase.Params>(scope) {

    override fun run(params: Params?): Flow<Unit> = when (params) {
        null -> throw MissingParamsException()
        else -> flow { repository.addMovie(params.movie) }
    }

    data class Params(
        val movie: MovieDetailModel,
    )
}