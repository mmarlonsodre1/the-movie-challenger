package com.example.domain.usecase.remote.movie

import com.example.domain.model.movie.MovieDetailModel
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

internal class GetMovieDetailUserCaseTest {

    @Mock
    private lateinit var response: MovieDetailModel

    @Mock
    private lateinit var repository: MovieRepository
    private lateinit var subject: GetMovieDetailUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetMovieDetailUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return MovieDetailModel value`() {
        stubOnSuccess()
        subject.run(
            GetMovieDetailUserCase.Params(1)
        ).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        whenever(repository.getMovieDetail(any())).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getMovieDetail(any())).thenThrow(Exception())
    }
}