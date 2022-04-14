package com.example.data.repository.local

import com.example.data.datasource.local.MovieLocalDataSource
import com.example.data.dummy.MovieDummy.LIST_MOVIE_DETAIL_DUMMY
import com.example.data.dummy.MovieDummy.MOVIE_DETAIL_DUMMY
import com.example.data.dummy.testFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class MovieDataLocalRepositoryImplTest {
    @Mock
    private lateinit var dataSource: MovieLocalDataSource
    private lateinit var repository: MovieDataLocalRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MovieDataLocalRepositoryImpl(dataSource)
    }

    @Test
    fun addMovie() {
        stubAddMovieSuccess()
        flowOf(repository.addMovie(MOVIE_DETAIL_DUMMY)).testFlow {
            assertEquals(Unit, this)
        }
    }

    private fun stubAddMovieSuccess() {
        doNothing().`when`(dataSource).addMovie(any())
    }

    @Test
    fun getAllMovies() {
        stubAllMovieSuccess()
        repository.getAllMovies().testFlow {
            assertEquals(LIST_MOVIE_DETAIL_DUMMY, this)
        }
    }

    private fun stubAllMovieSuccess() {
        whenever(dataSource.getAllMovies()).thenReturn(flowOf(LIST_MOVIE_DETAIL_DUMMY))
    }

    @Test
    fun deleteMovie() {
        stubDeleteMovieSuccess()
        flowOf(repository.deleteMovie(0)).testFlow {
            assertEquals(Unit, this)
        }
    }

    private fun stubDeleteMovieSuccess() {
        doNothing().`when`(dataSource).deleteMovie(any())
    }
}