package com.example.data_remote.datasource

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.data_remote.dummy.MovieDummy.MOVIE_DETAIL_DUMMY
import com.example.data_remote.dummy.MovieDummy.SEARCH_DUMMY
import com.example.data_remote.mapper.movie.MovieDetailMapper
import com.example.data_remote.mapper.search.SearchMapper
import com.example.data_remote.model.movie.MovieDetailResponse
import com.example.data_remote.model.search.SearchResponse
import com.example.data_remote.service.MovieWebService
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

internal class MovieRemoteDataSourceImplTest {

    private val requestWrapper by lazy { RequestWrapperTest() }

    private lateinit var dataSource: MovieRemoteDataSource

    private var webService: MovieWebService = mockk(relaxed = true)
    private val testModule = module {
        single { requestWrapper } bind RequestWrapper::class
        single { webService }
    }

    @Before
    fun setup() {
        startKoin { modules(testModule) }
        dataSource = MovieRemoteDataSourceImpl(webService)
    }

    @After
    fun afterTest() {
        stopKoin()
    }

    @Test
    fun getMovieDetail() = runBlocking {
        stubGetMovieDetail(MOVIE_DETAIL_DUMMY)

        val data = MovieDetailMapper.toDomain(MOVIE_DETAIL_DUMMY)
        val result = dataSource.getMovieDetail(1)

        assertEquals(data, result.first())
    }

    private suspend fun stubGetMovieDetail(
        dummyResponse: MovieDetailResponse
    ) {
        coEvery {
            (webService.getMovieDetail(any()))
        } returns dummyResponse
    }

    @Test
    fun getSimilarMovies() = runBlocking {
        stubGetSimilarMovies(SEARCH_DUMMY)

        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getSimilarMovies(1)
        assertEquals(data, result.first())
    }

    private suspend fun stubGetSimilarMovies(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getSimilarMovies(any()))
        } returns dummyResponse
    }

    @Test
    fun getTopMovies() = runBlocking {
        stubGetTopMovies(SEARCH_DUMMY)
        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getTopMovies()
        assertEquals(data, result.first())
    }

    private suspend fun stubGetTopMovies(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getTopMovies())
        } returns dummyResponse
    }

    @Test
    fun getTrendingMovies() = runBlocking {
        stubGetTrendingMovies(SEARCH_DUMMY)
        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getTrendingMovies()
        assertEquals(data, result.first())
    }

    private suspend fun stubGetTrendingMovies(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getTrendingMovies())
        } returns dummyResponse
    }

    @Test
    fun getPopularMovies() = runBlocking {
        stubGetPopularMovies(SEARCH_DUMMY)
        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getPopularMovies()
        assertEquals(data, result.first())
    }

    private suspend fun stubGetPopularMovies(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getPopularMovies())
        } returns dummyResponse
    }

    @Test
    fun getUpComingMovies() = runBlocking {
        stubGetUpComingMovies(SEARCH_DUMMY)
        val data = SearchMapper.toDomain(SEARCH_DUMMY)
        val result = dataSource.getUpComingMovies()
        assertEquals(data, result.first())
    }

    private suspend fun stubGetUpComingMovies(
        dummyResponse: SearchResponse
    ) {
        coEvery {
            (webService.getUpComingMovies())
        } returns dummyResponse
    }
}