package com.example.data_remote.datasource

import com.example.data.datasource.remote.MovieRemoteDataSource
import com.example.data_remote.mapper.movie.MovieDetailMapper
import com.example.data_remote.mapper.movie.MovieListMapper
import com.example.data_remote.mapper.search.SearchMapper
import com.example.data_remote.service.MovieWebService
import com.example.data_remote.utils.RequestWrapper
import com.example.domain.model.movie.MovieDetailModel
import com.example.domain.model.movie.MovieListModel
import com.example.domain.model.search.SearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieRemoteDataSourceImpl(
    private val webService: MovieWebService
) : MovieRemoteDataSource, KoinComponent {
    private val requestWrapper: RequestWrapper by inject()

    override fun getMovieDetail(movieId: Int): Flow<MovieDetailModel> = flow {
        emit(
            MovieDetailMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getMovieDetail(movieId)
                }
            )
        )
    }

    override fun getSimilarMovies(movieId: Int): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getSimilarMovies(movieId)
                }
            )
        )
    }

    override fun getTopMovies(): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getTopMovies()
                }
            )
        )
    }

    override fun getTrendingMovies(): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getTrendingMovies()
                }
            )
        )
    }

    override fun getPopularMovies(): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getPopularMovies()
                }
            )
        )
    }

    override fun getUpComingMovies(): Flow<SearchModel> = flow {
        emit(
            SearchMapper.toDomain(
                requestWrapper.wrapperGenericResponse {
                    webService.getUpComingMovies()
                }
            )
        )
    }
}