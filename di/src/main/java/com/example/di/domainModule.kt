package com.example.di

import com.example.domain.core.ThreadContextProvider
import com.example.domain.usecase.local.movie.AddMovieDataLocalUserCase
import com.example.domain.usecase.local.movie.GetAllMoviesDataLocalUserCase
import com.example.domain.usecase.local.movie.RemoveMovieDataLocalUserCase
import com.example.domain.usecase.remote.gender.GetGendersUserCase
import com.example.domain.usecase.remote.movie.GetMovieDetailUserCase
import com.example.domain.usecase.remote.movie.GetMoviesUserCase
import com.example.domain.usecase.remote.movie.GetSimilarMoviesUserCase
import com.example.domain.usecase.remote.search.SearchMoviesUserCase
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
        GetSimilarMoviesUserCase(
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

    factory { (scope: CoroutineScope) ->
        GetAllMoviesDataLocalUserCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        AddMovieDataLocalUserCase(
            scope = scope,
            repository = get()
        )
    }

    factory { (scope: CoroutineScope) ->
        RemoveMovieDataLocalUserCase(
            scope = scope,
            repository = get()
        )
    }
}
