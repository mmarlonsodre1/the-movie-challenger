package com.example.domain.usecase.local.movie

import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.repository.local.MovieDataLocalRepository
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
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class GetAllMoviesDataLocalUserCaseTest {

    @Mock
    private lateinit var response: List<MovieDetailModel>

    @Mock
    private lateinit var repository: MovieDataLocalRepository
    private lateinit var subject: GetAllMoviesDataLocalUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetAllMoviesDataLocalUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return List MovieDetailModel value`() {
        stubOnSuccess()
        subject.run().testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        whenever(repository.getAllMovies()).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getAllMovies()).thenThrow(Exception())
    }
}