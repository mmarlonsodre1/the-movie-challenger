package com.example.domain.usecase.remote.search

import com.example.domain.model.search.SearchModel
import com.example.domain.repository.remote.SearchRepository
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

internal class SearchMoviesUserCaseTest {

    @Mock
    private lateinit var response: SearchModel

    @Mock
    private lateinit var repository: SearchRepository
    private lateinit var subject: SearchMoviesUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = SearchMoviesUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return SearchModel value`() {
        stubOnSuccess()
        subject.run(
            SearchMoviesUserCase.Params("")
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
        whenever(repository.getSearch(any())).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getSearch(any())).thenThrow(Exception())
    }
}