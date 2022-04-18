package com.example.domain.usecase.local.movie

import com.example.domain.repository.local.MovieDataLocalRepository
import com.example.domain.util.testFlow
import com.example.domain.util.testModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class RemoveMovieDataLocalUserCaseTest {

    @Mock
    private lateinit var response: Unit

    @Mock
    private lateinit var repository: MovieDataLocalRepository
    private lateinit var subject: RemoveMovieDataLocalUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = RemoveMovieDataLocalUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return Unit value`() {
        stubOnSuccess()
        subject.run(params = RemoveMovieDataLocalUserCase.Params(1)).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        doNothing().`when`(repository).deleteMovie(any())
    }

    private fun stubOnError() {
        whenever(repository.deleteMovie(any())).thenThrow(Exception())
    }
}