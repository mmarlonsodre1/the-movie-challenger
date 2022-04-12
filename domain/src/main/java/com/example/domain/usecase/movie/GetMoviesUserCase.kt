package com.example.domain.usecase.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.movie.MovieListModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMoviesUserCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<MovieListModel, GetMoviesUserCase.Params>(scope) {


    override fun run(params: Params?): Flow<MovieListModel> = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getMovieList(params.gendeId)
    }

    data class Params(
        val gendeId: Int,
    )
}