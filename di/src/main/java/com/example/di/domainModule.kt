package com.example.di

import com.example.domain.core.ThreadContextProvider
import com.example.domain.usecase.gender.GetGendersUserCase
import com.example.domain.usecase.movie.GetMovieDetailUserCase
import com.example.domain.usecase.movie.GetMoviesUserCase
import com.example.domain.usecase.search.SearchMoviesUserCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    single {
        ThreadContextProvider()
    }

    factory { (scope: CoroutineScope) ->
        GetGendersUserCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetMoviesUserCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        GetMovieDetailUserCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        SearchMoviesUserCase(
            scope = scope,
            repository = get()
        )
    }
}
