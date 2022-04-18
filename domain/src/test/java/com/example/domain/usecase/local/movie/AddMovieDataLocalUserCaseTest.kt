package com.example.domain.usecase.local.movie

import com.example.domain.dummy.MovieDummy.MOVIE_DETAIL_DUMMY
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

internal class AddMovieDataLocalUserCaseTest {

    @Mock
    private lateinit var response: Unit

    @Mock
    private lateinit var repository: MovieDataLocalRepository
    private lateinit var subject: AddMovieDataLocalUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = AddMovieDataLocalUserCase(
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
        subject.run(params = AddMovieDataLocalUserCase.Params(MOVIE_DETAIL_DUMMY)).testFlow {
            assertEquals(response, this)
        }
    }

    @Test(expected = Exception::class)
    fun `WHEN fails MUST throw an exception`() {
        stubOnError()
        subject.run()
    }

    private fun stubOnSuccess() {
        doNothing().`when`(repository).addMovie(any())
    }

    private fun stubOnError() {
        whenever(repository.addMovie(any())).thenThrow(Exception())
    }
}