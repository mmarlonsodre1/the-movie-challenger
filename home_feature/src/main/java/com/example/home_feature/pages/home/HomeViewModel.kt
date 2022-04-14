package com.example.home_feature.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base_feature.core.*
import com.example.base_feature.utils.useCase
import com.example.base_feature.utils.viewState
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.remote.movie.GetMoviesUserCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent


class HomeViewModel : ViewModel(), KoinComponent {

    private val getMoviesUserCase: GetMoviesUserCase by useCase()
    private val _moviesViewState by viewState<Pair<GetMoviesUserCase.GetMoviesUserCaseEnum, SearchModel>>()
    val moviesViewState = _moviesViewState.asLiveData()
    private val allEnums = GetMoviesUserCase.GetMoviesUserCaseEnum.values()
    private var enumPosition = 0

    init {
        getGenders(allEnums[enumPosition])
    }

    fun getGenders(enum: GetMoviesUserCase.GetMoviesUserCaseEnum) {
        _moviesViewState.postLoading()
        getMoviesUserCase.invoke(
            params = GetMoviesUserCase.Params(allEnums[enumPosition]),
            onSuccess = {
                _moviesViewState.postSuccess(Pair(enum, it))
            },
            onError = {
                _moviesViewState.postError(it)
            },
            onCompleted = {
                viewModelScope.launch {
                    delay(300)
                    enumPosition += 1
                    if (allEnums.size > enumPosition) {
                        getGenders(allEnums[enumPosition])
                    }
                }
            }
        )
    }

    fun resetViewStates() {
        _moviesViewState.postNeutral()
    }
}