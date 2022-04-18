package com.example.domain.usecase.remote.gender

import com.example.domain.model.gender.GenderListModel
import com.example.domain.repository.remote.GenderRepository
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

internal class GetGendersUserCaseTest {

    @Mock
    private lateinit var response: GenderListModel

    @Mock
    private lateinit var repository: GenderRepository
    private lateinit var subject: GetGendersUserCase

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        startKoin { modules(testModule) }
        subject = GetGendersUserCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `WHEN succeed MUST return GenderListModel value`() {
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
        whenever(repository.getGenderList()).thenReturn(flowOf(response))
    }

    private fun stubOnError() {
        whenever(repository.getGenderList()).thenThrow(Exception())
    }
}