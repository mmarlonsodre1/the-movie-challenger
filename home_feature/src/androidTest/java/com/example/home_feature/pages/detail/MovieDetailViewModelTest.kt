package com.example.home_feature.pages.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.base_feature.core.assertNeutral
import com.example.base_feature.core.getOrAwaitValueAndAssert
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.search.SearchModel
import com.example.domain.usecase.local.movie.AddMovieDataLocalUserCase
import com.example.domain.usecase.local.movie.GetAllMoviesDataLocalUserCase
import com.example.domain.usecase.local.movie.RemoveMovieDataLocalUserCase
import com.example.domain.usecase.remote.movie.GetMovieDetailUserCase
import com.example.domain.usecase.remote.movie.GetSimilarMoviesUserCase
import com.example.home_feature.pages.detail.MovieDetailViewModelDummy.MOVIE_DETAIL_DUMMY
import com.example.home_feature.pages.detail.MovieDetailViewModelDummy.MOVIE_DETAIL_EMPTY_LIST_DUMMY
import com.example.home_feature.pages.detail.MovieDetailViewModelDummy.MOVIE_DETAIL_LIST_DUMMY
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

internal class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getMovieDetailUserCase: GetMovieDetailUserCase = mockk()
    private val getSimilarMoviesUserCase: GetSimilarMoviesUserCase = mockk()
    private val addMovieDataLocalUserCase: AddMovieDataLocalUserCase = mockk()
    private val getAllMoviesDataLocalUserCase: GetAllMoviesDataLocalUserCase = mockk()
    private val removeMovieDataLocalUserCase: RemoveMovieDataLocalUserCase = mockk()

    private val testModule = module {
        single { getMovieDetailUserCase }
        single { getSimilarMoviesUserCase }
        single { addMovieDataLocalUserCase }
        single { getAllMoviesDataLocalUserCase }
        single { removeMovieDataLocalUserCase }
    }

    @Before
    fun before() {
        startKoin { modules(testModule) }
        viewModel = MovieDetailViewModel()
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun getMovieDetail() {
        stubGetMovieDetailOnSuccess()
        viewModel.getMovieDetail(1)
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()

        stubGetMovieDetailOnSuccessEmpty()
        stubGetMovieDetailRemoteOnSuccess()
        viewModel.getMovieDetail(1)
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()

        stubGetMovieDetailOnSuccessEmpty()
        stubGetMovieDetailOnError()
        viewModel.getMovieDetail(1)
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubGetMovieDetailOnSuccess() {
        every {
            getAllMoviesDataLocalUserCase(onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(List<MovieDetailModel>) -> Unit>().invoke(MOVIE_DETAIL_LIST_DUMMY)
        }
    }

    private fun stubGetMovieDetailOnSuccessEmpty() {
        every {
            getAllMoviesDataLocalUserCase(onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(List<MovieDetailModel>) -> Unit>().invoke(MOVIE_DETAIL_EMPTY_LIST_DUMMY)
        }
    }

    private fun stubGetMovieDetailRemoteOnSuccess() {
        every {
            getMovieDetailUserCase(params = any(), onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(MovieDetailModel) -> Unit>().invoke(MOVIE_DETAIL_DUMMY)
        }
    }

    private fun stubGetMovieDetailOnError() {
        every {
            getMovieDetailUserCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun addMovie() {
        stubAddMovieOnSuccess()
        stubGetMovieDetailOnSuccess()
        viewModel.getMovieDetail(1)
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()
        viewModel.addMovie()
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()

        stubAddMovieOnError()
        stubGetMovieDetailOnSuccessEmpty()
        stubGetMovieDetailOnError()
        viewModel.getMovieDetail(1)
        viewModel.addMovie()
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubAddMovieOnSuccess() {
        every {
            addMovieDataLocalUserCase(params = any(), onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(Unit) -> Unit>().invoke(Unit)
        }
    }

    private fun stubAddMovieOnError() {
        every {
            addMovieDataLocalUserCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun removeMovie() {
        stubRemoveMovieOnSuccess()
        stubGetMovieDetailOnSuccess()
        viewModel.getMovieDetail(1)
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()
        viewModel.removeMovie()
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert()

        stubRemoveMovieOnError()
        stubGetMovieDetailOnSuccessEmpty()
        stubGetMovieDetailOnError()
        viewModel.getMovieDetail(1)
        viewModel.removeMovie()
        viewModel.movieDetailViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubRemoveMovieOnSuccess() {
        every {
            removeMovieDataLocalUserCase(params = any(), onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(Unit) -> Unit>().invoke(Unit)
        }
    }

    private fun stubRemoveMovieOnError() {
        every {
            removeMovieDataLocalUserCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun getSimilarMovies() {
        stubGetSimilarMoviesOnSuccess()
        viewModel.getSimilarMovies(1)
        viewModel.similarMoviesViewState.getOrAwaitValueAndAssert()

        stubGetSimilarMoviesOnError()
        viewModel.getSimilarMovies(1)
        viewModel.similarMoviesViewState.getOrAwaitValueAndAssert(false)
    }

    private fun stubGetSimilarMoviesOnSuccess() {
        every {
            getSimilarMoviesUserCase(params = any(), onError = any(), onSuccess = captureLambda())
        } answers {
            lambda<(SearchModel) -> Unit>().invoke(SEARCH_DUMMY)
        }
    }

    private fun stubGetSimilarMoviesOnError() {
        every {
            getSimilarMoviesUserCase(
                params = any(), onError = captureLambda(), onSuccess = any()
            )
        } answers {
            lambda<(Throwable) -> Unit>().invoke(Throwable())
        }
    }

    @Test
    fun resetViewStates() {
        viewModel.resetViewStates()
        viewModel.movieDetailViewState.assertNeutral()
        viewModel.similarMoviesViewState.assertNeutral()
    }
}