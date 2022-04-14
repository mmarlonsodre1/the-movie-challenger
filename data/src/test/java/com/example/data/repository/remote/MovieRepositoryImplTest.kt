package com.example.data.repository.remote

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.data.dummy.MovieDummy.MOVIE_DETAIL_DUMMY
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

internal class MovieRepositoryImplTest {
    @Mock
    private lateinit var dataSource: MovieRemoteDataSource
    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MovieRepositoryImpl(dataSource)
    }

    @Test
    fun getMovieDetail() {
        stubMovieDetailSuccess()
        repository.getMovieDetail(1).testFlow {
            assertEquals(MOVIE_DETAIL_DUMMY, this)
        }
    }

    private fun stubMovieDetailSuccess() {
        whenever(dataSource.getMovieDetail(any())).thenReturn(flowOf(MOVIE_DETAIL_DUMMY))
    }

    @Test
    fun getSimilarMovies() {
        stubSimilarMoviesSuccess()
        repository.getSimilarMovies(1).testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubSimilarMoviesSuccess() {
        whenever(dataSource.getSimilarMovies(any())).thenReturn(flowOf(SEARCH_DUMMY))
    }

    @Test
    fun getTopMovies() {
        stubTopMoviesSuccess()
        repository.getTopMovies().testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubTopMoviesSuccess() {
        whenever(dataSource.getTopMovies()).thenReturn(flowOf(SEARCH_DUMMY))
    }

    @Test
    fun getTrendingMovies() {
        stubTrendingMoviesSuccess()
        repository.getTrendingMovies().testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubTrendingMoviesSuccess() {
        whenever(dataSource.getTrendingMovies()).thenReturn(flowOf(SEARCH_DUMMY))
    }

    @Test
    fun getPopularMovies() {
        stubPopularMoviesSuccess()
        repository.getPopularMovies().testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubPopularMoviesSuccess() {
        whenever(dataSource.getPopularMovies()).thenReturn(flowOf(SEARCH_DUMMY))
    }

    @Test
    fun getUpComingMovies() {
        stubUpComingMoviesSuccess()
        repository.getUpComingMovies().testFlow {
            assertEquals(SEARCH_DUMMY, this)
        }
    }

    private fun stubUpComingMoviesSuccess() {
        whenever(dataSource.getUpComingMovies()).thenReturn(flowOf(SEARCH_DUMMY))
    }
}