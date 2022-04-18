package com.example.domain.usecase.remote.movie

import com.example.domain.model.search.SearchModel
import com.example.domain.repository.remote.MovieRepository
import com.example.domain.util.testFlow
import com.example.domain.util.testModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class GetMoviesUserCaseTest {

    @Mock
    private lateinit var response: SearchModel

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var subject: GetMoviesUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetMoviesUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST on GetMoviesUserCaseEnum TOP_MOVIES return SearchModel value`() {
        stubOnTopMoviesSuccess()
        subject.run(
            GetMoviesUserCase.Params(GetMoviesUserCase.GetMoviesUserCaseEnum.TOP_MOVIES)
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test
    fun `WHEN succeed MUST on GetMoviesUserCaseEnum POPULAR_MOVIES return SearchModel value`() {
        stubOnPopularMoviesSuccess()
        subject.run(
            GetMoviesUserCase.Params(GetMoviesUserCase.GetMoviesUserCaseEnum.POPULAR_MOVIES)
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test
    fun `WHEN succeed MUST on GetMoviesUserCaseEnum TRENDING_MOVIES return SearchModel value`() {
        stubOnTrendingMoviesSuccess()
        subject.run(
            GetMoviesUserCase.Params(GetMoviesUserCase.GetMoviesUserCaseEnum.TRENDING_MOVIES)
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test
    fun `WHEN succeed MUST on GetMoviesUserCaseEnum UP_COMING_MOVIES return SearchModel value`() {
        stubOnUpComingMoviesSuccess()
        subject.run(
            GetMoviesUserCase.Params(GetMoviesUserCase.GetMoviesUserCaseEnum.UP_COMING_MOVIES)
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnPopularMoviesSuccess() {
        whenever(repository.getPopularMovies()).thenReturn(flowOf(response))
    }

    private fun stubOnTopMoviesSuccess() {
        whenever(repository.getTopMovies()).thenReturn(flowOf(response))
    }

    private fun stubOnTrendingMoviesSuccess() {
        whenever(repository.getTrendingMovies()).thenReturn(flowOf(response))
    }

    private fun stubOnUpComingMoviesSuccess() {
        whenever(repository.getUpComingMovies()).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getMovieDetail(any())).thenThrow(Exception())
    }
}