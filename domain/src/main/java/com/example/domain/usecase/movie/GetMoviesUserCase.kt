package com.example.domain.usecase.movie

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.search.SearchModel
import com.example.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMoviesUserCase(
    scope: CoroutineScope,
    private val repository: MovieRepository
) : UseCase<SearchModel, GetMoviesUserCase.Params>(scope) {


    override fun run(params: Params?): Flow<SearchModel> = when (params) {
        null -> throw MissingParamsException()
        else -> when(params.enum) {
            GetMoviesUserCaseEnum.TOP_MOVIES -> repository.getTopMovies()
            GetMoviesUserCaseEnum.TRENDING_MOVIES -> repository.getTrendingMovies()
            GetMoviesUserCaseEnum.POPULAR_MOVIES -> repository.getPopularMovies()
            GetMoviesUserCaseEnum.UP_COMING_MOVIES -> repository.getUpComingMovies()
        }
    }

    data class Params(
        val enum: GetMoviesUserCaseEnum,
    )

    enum class GetMoviesUserCaseEnum(val title: String) {
        TOP_MOVIES("Melhores filmes"),
        TRENDING_MOVIES("Filmes em alta"),
        POPULAR_MOVIES("Filmes populares"),
        UP_COMING_MOVIES("Próximos filmes a serem lançados")
    }
}