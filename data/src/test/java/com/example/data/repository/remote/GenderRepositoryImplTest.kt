package com.example.data.repository.remote

import com.example.data.datasource.remote.GenderRemoteDataSource
import com.example.data.dummy.GenderDummy.GENDER_LIST_DUMMY
import com.example.data.dummy.testFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class GenderRepositoryImplTest {
    @Mock
    private lateinit var dataSource: GenderRemoteDataSource
    private lateinit var repository: GenderRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = GenderRepositoryImpl(dataSource)
    }

    @Test
    fun getGenderList() {
        stubGenderListSuccess()
        repository.getGenderList().testFlow {
            assertEquals(GENDER_LIST_DUMMY, this)
        }
    }

    private fun stubGenderListSuccess() {
        whenever(dataSource.getGenderList()).thenReturn(flowOf(GENDER_LIST_DUMMY))
    }
}