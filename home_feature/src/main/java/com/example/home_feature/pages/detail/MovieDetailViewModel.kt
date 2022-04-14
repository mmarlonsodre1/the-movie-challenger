package com.example.home_feature.pages.detail

import androidx.lifecycle.ViewModel
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.movie.GetMovieDetailUserCase
import com.example.domain.usecase.movie.GetSimilarMoviesUserCase
import org.koin.core.KoinComponent


class MovieDetailViewModel : ViewModel(), KoinComponent {

    private val getMovieDetailUserCase: GetMovieDetailUserCase by useCase()
    private val getSimilarMoviesUserCase: GetSimilarMoviesUserCase by useCase()

    private val _movieDetailViewState by viewState<MovieDetailModel>()
    private val _similarMoviesViewState by viewState<SearchModel>()

    val movieDetailViewState = _movieDetailViewState.asLiveData()
    val similarMoviesViewState = _similarMoviesViewState.asLiveData()

    fun getMovieDetail(movieId: Int) {
        _movieDetailViewState.postLoading()
        getMovieDetailUserCase.invoke(
            params = GetMovieDetailUserCase.Params(movieId),
            onSuccess = {
                _movieDetailViewState.postSuccess(it)
            },
            onError = {
                _movieDetailViewState.postError(it)
            }
        )
    }

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