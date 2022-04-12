package com.example.domain.usecase.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMovieDetailUserCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<MovieDetailModel, GetMovieDetailUserCase.Params>(scope) {


    override fun run(params: Params?): Flow<MovieDetailModel> = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getMovieDetail(params.movieId)
    }

    data class Params(
        val movieId: Int,
    )
}