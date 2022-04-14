package com.example.data_remote.datasource

import com.example.data.datasource.remote.GenderRemoteDataSource
import com.example.data_remote.dummy.GenderDummy.GENDER_LIST_DUMMY
import com.example.data_remote.mapper.gender.GenderMapper
import com.example.data_remote.model.gender.GenderListResponse
import com.example.data_remote.service.GenderWebService
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

internal class GenderRemoteDataSourceImplTest {
    private val requestWrapper by lazy { RequestWrapperTest() }

    private lateinit var dataSource: GenderRemoteDataSource

    private var webService: GenderWebService = mockk(relaxed = true)
    private val testModule = module {
        single { requestWrapper } bind RequestWrapper::class
        single { webService }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        dataSource = GenderRemoteDataSourceImpl(webService)
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun getGenderList() = runBlocking {
        stubGetGenders(GENDER_LIST_DUMMY)

        val data = GenderMapper.toDomain(GENDER_LIST_DUMMY)
        val result = dataSource.getGenderList()

        assertEquals(data, result.first())
    }

    private suspend fun stubGetGenders(
        dummyResponse: GenderListResponse
    ) {
        coEvery {
            (webService.getGenderList())
        } returns dummyResponse
    }
}