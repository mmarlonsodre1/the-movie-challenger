package com.example.data.repository.remote

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data.dummy.MovieDummy.SEARCH_DUMMY
import com.example.data.dummy.testFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class SearchRepositoryImplTest {
    @Mock
    private lateinit var dataSource: SearchRemoteDataSource
    private lateinit var repository: SearchRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = SearchRepositoryImpl(dataSource)
    }

    @Test
    fun getSearch() {
        stubSearchSuccess()
        repository.getSearch("").testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubSearchSuccess() {
        whenever(dataSource.getSearch(any())).thenReturn(flowOf(SEARCH_DUMMY))
    }
}