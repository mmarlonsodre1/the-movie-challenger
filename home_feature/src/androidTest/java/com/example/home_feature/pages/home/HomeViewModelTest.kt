package com.example.home_feature.pages.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.core.assertNeutral
import com.example.base_feature.core.getOrAwaitValueAndAssert
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.remote.movie.GetMoviesUserCase
import com.example.home_feature.pages.detail.MovieDetailViewModelDummy.SEARCH_DUMMY
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

internal class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getMoviesUserCase: GetMoviesUserCase = mockk()

    private val testModule = module {
        single { getMoviesUserCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = HomeViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getMoviesViewState() {
        stubOnSuccess()
        viewModel.getGenders()
        viewModel.moviesViewState.getOrAwaitValueAndAssert()

        stubOnError()
        viewModel.getGenders()
        viewModel.moviesViewState.getOrAwaitValueAndAssert(false)
    }

    @Test
    fun resetViewStates() {
        viewModel.resetViewStates()
        viewModel.moviesViewState.assertNeutral()
    }

    private fun stubOnSuccess() {
        every {
            getMoviesUserCase(params = any(), onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(SearchModel) -> Unit>().invoke(SEARCH_DUMMY)
        }
    }

    private fun stubOnError() {
        every {
            getMoviesUserCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }
}