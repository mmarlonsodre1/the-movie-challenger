package com.example.list_feature.my_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.core.assertNeutral
import com.example.base_feature.core.getOrAwaitValueAndAssert
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.usecase.local.movie.GetAllMoviesDataLocalUserCase
import com.example.list_feature.my_list.MyListViewModelDummy.MOVIE_DETAIL_LIST_DUMMY
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

internal class MyListViewModelTest {

    private lateinit var viewModel: MyListViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getMoviesUserCase: GetAllMoviesDataLocalUserCase = mockk()

    private val testModule = module {
        single { getMoviesUserCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = MyListViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getMovies() {
        stubOnSuccess()
        viewModel.getMovies()
        viewModel.moviesViewState.getOrAwaitValueAndAssert()

        stubOnError()
        viewModel.getMovies()
        viewModel.moviesViewState.getOrAwaitValueAndAssert(false)
    }

    @Test
    fun resetViewStates() {
        viewModel.resetViewStates()
        viewModel.moviesViewState.assertNeutral()
    }

    private fun stubOnSuccess() {
        every {
            getMoviesUserCase(onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(List<MovieDetailModel>) -> Unit>().invoke(MOVIE_DETAIL_LIST_DUMMY)
        }
    }

    private fun stubOnError() {
        every {
            getMoviesUserCase(onError = captureLambda(), onSuccess = any())
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }
}