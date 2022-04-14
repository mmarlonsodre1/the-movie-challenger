package com.example.list_feature.my_list

import androidx.lifecycle.ViewModel
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.usecase.local.movie.GetAllMoviesDataLocalUserCase
import org.koin.core.KoinComponent


class MyListViewModel : ViewModel(), KoinComponent {

    private val getAllMoviesDataLocalUserCase: GetAllMoviesDataLocalUserCase by useCase()
    private val _moviesViewState by viewState<List<MovieDetailModel>>()
    val moviesViewState = _moviesViewState.asLiveData()

    fun getMovies() {
        _moviesViewState.postLoading()
        getAllMoviesDataLocalUserCase.invoke(
            onSuccess = {
                _moviesViewState.postSuccess(it)
            },
            onError = {
                _moviesViewState.postError(it)
            }
        )
    }

    fun resetViewStates() {
        _moviesViewState.postNeutral()
    }
}