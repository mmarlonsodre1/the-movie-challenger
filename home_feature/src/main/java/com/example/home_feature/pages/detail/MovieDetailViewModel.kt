package com.example.home_feature.pages.detail

import androidx.lifecycle.ViewModel
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.movie.GetSimilarMoviesUserCase
import org.koin.core.KoinComponent


class MovieDetailViewModel : ViewModel(), KoinComponent {

    private val getSimilarMoviesUserCase: GetSimilarMoviesUserCase by useCase()
    private val _similarMoviesViewState by viewState<SearchModel>()
    val similarMoviesViewState = _similarMoviesViewState.asLiveData()

    fun getSimilarMovies(movieId: Int) {
        _similarMoviesViewState.postLoading()
        getSimilarMoviesUserCase.invoke(
            params = GetSimilarMoviesUserCase.Params(movieId),
            onSuccess = {
                _similarMoviesViewState.postSuccess(it)
            },
            onError = {
                _similarMoviesViewState.postError(it)
            }
        )
    }

    fun resetViewStates() {
        _similarMoviesViewState.postNeutral()
    }
}