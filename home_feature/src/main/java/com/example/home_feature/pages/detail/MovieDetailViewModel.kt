package com.example.home_feature.pages.detail

import androidx.lifecycle.ViewModel
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.local.movie.AddMovieDataLocalUserCase
import com.example.domain.usecase.local.movie.GetAllMoviesDataLocalUserCase
import com.example.domain.usecase.local.movie.RemoveMovieDataLocalUserCase
import com.example.domain.usecase.remote.movie.GetMovieDetailUserCase
import com.example.domain.usecase.remote.movie.GetSimilarMoviesUserCase
import org.koin.core.KoinComponent


class MovieDetailViewModel : ViewModel(), KoinComponent {

    private val getMovieDetailUserCase: GetMovieDetailUserCase by useCase()
    private val getSimilarMoviesUserCase: GetSimilarMoviesUserCase by useCase()
    private val addMovieDataLocalUserCase: AddMovieDataLocalUserCase by useCase()
    private val getAllMoviesDataLocalUserCase: GetAllMoviesDataLocalUserCase by useCase()
    private val removeMovieDataLocalUserCase: RemoveMovieDataLocalUserCase by useCase()

    private val _movieDetailViewState by viewState<Pair<MovieDetailModel, Boolean>>()
    private val _similarMoviesViewState by viewState<SearchModel>()

    val movieDetailViewState = _movieDetailViewState.asLiveData()
    val similarMoviesViewState = _similarMoviesViewState.asLiveData()

    fun getMovieDetail(movieId: Int) {
        _movieDetailViewState.postLoading()
        getAllMoviesDataLocalUserCase.invoke(
            onSuccess = {
                val movie = it.firstOrNull { movie1 -> movie1.id == movieId }
                if (movie == null) getMovieDetailRemote(movieId)
                else _movieDetailViewState.postSuccess(Pair(movie, true))
            },
            onError = {
                getMovieDetailRemote(movieId)
            }
        )
    }

    private fun getMovieDetailRemote(movieId: Int) {
        _movieDetailViewState.postLoading()
        getMovieDetailUserCase.invoke(
            params = GetMovieDetailUserCase.Params(movieId),
            onSuccess = {
                _movieDetailViewState.postSuccess(Pair(it, false))
            },
            onError = {
                _movieDetailViewState.postError(it)
            }
        )
    }

    fun addMovie() {
        _movieDetailViewState.value?.data?.let { movie ->
            addMovieDataLocalUserCase.invoke(
                params = AddMovieDataLocalUserCase.Params(movie.first),
                onSuccess = {
                    getMovieDetail(movie.first.id!!)
                }
            )
        }
    }

    fun removeMovie() {
        _movieDetailViewState.value?.data?.let { movie ->
            removeMovieDataLocalUserCase.invoke(
                params = RemoveMovieDataLocalUserCase.Params(movie.first.id!!),
                onSuccess = {
                    getMovieDetail(movie.first.id!!)
                }
            )
        }
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