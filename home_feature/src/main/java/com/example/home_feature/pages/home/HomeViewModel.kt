package com.example.home_feature.pages.home

import androidx.lifecycle.ViewModel
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.remote.movie.GetMoviesUserCase
import org.koin.core.KoinComponent


class HomeViewModel : ViewModel(), KoinComponent {

    private val getMoviesUserCase: GetMoviesUserCase by useCase()
    private val _moviesViewState by viewState<Pair<GetMoviesUserCase.GetMoviesUserCaseEnum, SearchModel>>()
    val moviesViewState = _moviesViewState.asLiveData()
    val allEnums = GetMoviesUserCase.GetMoviesUserCaseEnum.values()
    var enumPosition = 0

    fun getGenders() {
        val enum = allEnums[enumPosition]
        _moviesViewState.postLoading()
        getMoviesUserCase.invoke(
            params = GetMoviesUserCase.Params(enum),
            onSuccess = {
                _moviesViewState.postSuccess(Pair(enum, it))
            },
            onError = {
                _moviesViewState.postError(it)
            }
        )
        enumPosition += 1
    }

    fun resetViewStates() {
        _moviesViewState.postNeutral()
    }
}