package com.example.data_remote.datasource

import com.example.data.datasource.remote.SearchRemoteDataSource
import com.example.data_remote.dummy.MovieDummy.SEARCH_DUMMY
import com.example.data_remote.mapper.search.SearchMapper
import com.example.data_remote.model.search.SearchResponse
import com.example.data_remote.service.SearchWebService
import com.example.data_remote.util.RequestWrapperTest
import com.example.data_remote.utils.RequestWrapper
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module

internal class SearchRemoteDataSourceImplTest {
    private val requestWrapper by lazy { RequestWrapperTest() }

    private lateinit var dataSource: SearchRemoteDataSource

    private var webService: SearchWebService = mockk(relaxed = true)
    private val testModule = module {
        single { requestWrapper } bind RequestWrapper::class
        single { webService }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        dataSource = SearchRemoteDataSourceImpl(webService)
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun getSearch() = runBlocking {
        stubGetSearch(SEARCH_DUMMY)

        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getSearch("")

        assertEquals(data, result.first())
    }

    private suspend fun stubGetSearch(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getSearch(any()))
        } returns dummyResponse
    }
}