package com.example.domain.usecase.search

import com.example.domain.core.UseCase
import com.example.domain.exception.MissingParamsException
import com.example.domain.model.search.SearchModel
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class SearchMoviesUserCase(
    scope: CoroutineScope,
    private val repository: SearchRepository
) : UseCase<SearchModel, SearchMoviesUserCase.Params>(scope) {


    override fun run(params: Params?): Flow<SearchModel> = when (params) {
        null -> throw MissingParamsException()
        else -> repository.getSearch(params.query)
    }

    data class Params(
        val query: String,
    )
}